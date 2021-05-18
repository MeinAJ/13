/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.distributed.id.snowflake.plus;

import com.aj.distributed.id.init.WorkerInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CachedGenerator
 *
 * @author An Jun
 * @date 2021-05-18
 */
@SuppressWarnings("ALL")
public class CachedGenerator {

    private WorkerInfo workerInfo;

    public CachedGenerator(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
        this.init();
    }

    //  物理节点ID长度
    private final long workerIdBits = 10L;

    //  序列号12位， 4095，同毫秒内生成不同id的最大个数
    private final long sequenceBits = 12L;

    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    //  数据中心节点左移17位
    private final long workerIdShift = sequenceBits;

    //  时间毫秒数左移22位
    private final long timestampLeftShift = sequenceBits + workerIdBits;

    /**
     * 当使用了超过百分之50的时候,就不填满
     */
    private final int usedCapacity = 50;

    private final int capacity = 4096;

    private final Long[] ringBuffer = new Long[capacity];

    private final Boolean[] ringBufferFlag = new Boolean[capacity];

    private AtomicInteger cursor = new AtomicInteger(-1);

    private AtomicInteger tail = new AtomicInteger(0);

    private final ReentrantLock outOfIndexLock = new ReentrantLock();

    private final AtomicLong lastSecond = new AtomicLong(System.currentTimeMillis() / 1000 - 1621267200L);

    private final ScheduledExecutorService threadPoolExecutor = Executors.newSingleThreadScheduledExecutor();

    private final ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();

    private final AtomicBoolean generating = new AtomicBoolean(false);

    private void init() {
        long lastTimeStamp = lastSecond.getAndIncrement();

        long prefix = ((lastTimeStamp) << timestampLeftShift) | (workerInfo.getWorkerId() << workerIdShift);

        for (int index = 0; index < capacity; index++) {
            long id = idGenerator(prefix, index);
            ringBuffer[index] = id;
            ringBufferFlag[index] = true;
        }

        //定时调度去检查可用id少于百分之50时，就去生成百分之50放入缓存中
        threadPoolExecutor.scheduleWithFixedDelay(new TaskThread(), 0, 500, TimeUnit.MILLISECONDS);
    }

    private long idGenerator(long prefix, int index) {
        return prefix | (index + 1) & sequenceMask;
    }

    private class TaskThread implements Runnable {
        @Override
        public void run() {
            if (generating.compareAndSet(false, true)) {
                boolean flag = emptyMoreThan50();
                if (flag) {
                    //开始缓存一半的id

                    long lastTimeStamp = lastSecond.getAndIncrement();

                    long prefix = ((lastTimeStamp) << timestampLeftShift) | (workerInfo.getWorkerId() << workerIdShift);

                    for (int i = 0; i < capacity >> 1; i++) {
                        int index = tail.incrementAndGet();
                        index = getArrayValidIndex(index);

                        ringBuffer[index] = idGenerator(prefix, i);
                        ringBufferFlag[index] = true;
                    }
                }
                generating.set(false);
            }
        }

        private int getArrayValidIndex(int index) {
            if (index >= capacity) {
                index = 0;
            }
            return index;
        }
    }

    public Long nextId() throws Exception {
        int index = cursor.getAndIncrement();
        if (index + 1 == capacity) {
            if (outOfIndexLock.tryLock()) {
                //尝试获取锁成功
                System.out.println("尝试获取锁成功");
                cursor.set(-1);
            }
            outOfIndexLock.unlock();
            return null;
        }
        Boolean flag = ringBufferFlag[index];
        if (!flag) {
            return null;
        }
        ringBufferFlag[index] = false;
        return ringBuffer[index];
    }

    private boolean emptyMoreThan50() {
        int cursorCount = cursor.get();
        int tailCount = tail.get();
        if (cursorCount > tailCount) {
            int abs = Math.abs(cursorCount - tailCount);
            if (abs >= (capacity >> 1)) {
                return true;
            }
        } else if (cursorCount < tailCount) {
            int usedId = capacity - tailCount + cursorCount;
            if (usedId >= (capacity >> 1)) {
                return true;
            }
        } else {
            //同时出现时，可能是没有任何值获取id，或者是已经用完了
            //检查一下数组位置是false时，就消耗完了id
            if (ringBufferFlag[0] == false) {
                return true;
            }
        }
        return false;
    }

}
