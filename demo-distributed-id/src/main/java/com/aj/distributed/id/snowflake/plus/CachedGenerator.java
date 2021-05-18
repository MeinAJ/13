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

    private final int threshold = capacity >> 1;

    private final Long[] ringBuffer = new Long[capacity];

    private final Boolean[] ringBufferFlag = new Boolean[capacity];

    private AtomicInteger cursor = new AtomicInteger(-1);

    private AtomicInteger tail = new AtomicInteger(0);

    private AtomicInteger used = new AtomicInteger(0);

    private final ReentrantLock outOfIndexLock = new ReentrantLock();

    private final AtomicLong lastSecond = new AtomicLong(System.currentTimeMillis() / 1000 - 1621267200L);

    private final ScheduledExecutorService threadPoolExecutor = Executors.newSingleThreadScheduledExecutor();

    private final ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();

    private final AtomicBoolean generating = new AtomicBoolean(false);

    private void init() {

        long prefix = getIncrementPrefix();

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

                    long prefix = getIncrementPrefix();

                    for (int i = 0; i < threshold; i++) {
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

    private long getIncrementPrefix() {
        long lastTimeStamp = lastSecond.getAndIncrement();
        return ((lastTimeStamp) << timestampLeftShift) | (workerInfo.getWorkerId() << workerIdShift);
    }

    public Long nextId() throws Exception {
        int index = cursor.getAndIncrement();
        if (index + 1 >= capacity) {
            if (outOfIndexLock.tryLock()) {
                //尝试获取锁成功
                System.out.println("尝试获取锁成功");
                cursor.set(0);
                index = 0;
                outOfIndexLock.unlock();
            }
        }
        if (ringBufferFlag[index]) {
            ringBufferFlag[index] = false;
            used.incrementAndGet();
            return ringBuffer[index];
        }
        return null;
    }

    private boolean emptyMoreThan50() {
        return used.get() >= threshold;
    }

}
