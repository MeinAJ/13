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
    private final long workerIdBits = 8L;

    //  序列号12位， 4095，同毫秒内生成不同id的最大个数
    private final long sequenceBits = 14L;

    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    //  数据中心节点左移17位
    private final long workerIdShift = sequenceBits;

    //  时间毫秒数左移22位
    private final long timestampLeftShift = sequenceBits + workerIdBits;

    /**
     * 当使用了超过百分之50的时候,就不填满
     */
    private final int usedCapacity = 50;

    private final int capacity = 1 << sequenceBits;

    private final int threshold = capacity >> 1;

    private final Long[] ringBuffer = new Long[capacity];

    private final Boolean[] ringBufferFlag = new Boolean[capacity];

    private int cursor;

    private int tail;

    private AtomicInteger used = new AtomicInteger(0);

    private final ReentrantLock nextIdLock = new ReentrantLock(true);

    private final AtomicLong lastSecond = new AtomicLong(System.currentTimeMillis() / 1000 - 1621267200L);

    private final ScheduledExecutorService threadPoolExecutor = Executors.newSingleThreadScheduledExecutor();

    private final AtomicBoolean generating = new AtomicBoolean(false);

    private void init() {

        long prefix = getPrefix();

        for (int index = 0; index < capacity; index++) {
            long id = idGenerator(prefix, index);
            ringBuffer[index] = id;
            ringBufferFlag[index] = true;
        }
        //定时1s调度去检查可用id少于百分之50时，就去生成百分之50放入缓存中
        threadPoolExecutor.scheduleWithFixedDelay(new RingBufferFillTask(), 0, 1000, TimeUnit.MILLISECONDS);
    }

    private long idGenerator(long prefix, int index) {
        return prefix | (index + 1) & sequenceMask;
    }

    private class RingBufferFillTask implements Runnable {

        @Override
        public void run() {
            if (generating.compareAndSet(false, true)) {
                if (emptyMoreThan50()) {
                    System.out.println("已使用=" + used.get());
                    //开始缓存一半的id
                    long prefix = getPrefix();
                    for (int i = 0; i < threshold; i++) {
                        int newIndex = getValidIndex(tail);
                        if (tail != newIndex) {
                            //newIndex肯定为0,tail肯定为capacity
                            tail = newIndex;
                        }
                        ringBuffer[newIndex] = idGenerator(prefix, i);
                        ringBufferFlag[newIndex] = true;
                        tail++;
                    }

                    int usedCount = used.get();
                    //cas设置已使用id
                    while (used.compareAndSet(usedCount, (usedCount - threshold))) {
                        //do nothing
                        //空转
                    }
                    System.out.println("生成id后,已使用=" + used.get());
                }
                generating.set(false);
            }
        }

        private int getValidIndex(int index) {
            if (index >= capacity) {
                index = 0;
            }
            return index;
        }

    }

    private long getPrefix() {
        long lastTimeStamp = lastSecond.getAndIncrement();
        return ((lastTimeStamp) << timestampLeftShift) | (workerInfo.getWorkerId() << workerIdShift);
    }

    public Long nextId() throws Exception {
        //加锁
        try {
            nextIdLock.lock();
            if (isOutOfBound(cursor)) {
                cursor = 0;
            }
            if (ringBufferFlag[cursor]) {
                //这个cursor位置的id是可用的
                ringBufferFlag[cursor] = false;
                //cas设置已使用id
                int usedCount = used.get();
                while (used.compareAndSet(usedCount, (usedCount + 1))) {
                }
                System.out.println("cursor=" + cursor);
                System.out.println("used=" + used.get());
                return ringBuffer[cursor++];
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nextIdLock.unlock();
        }
        return null;
    }

    private boolean isOutOfBound(int index) {
        return index >= capacity || index < 0;
    }

    private boolean emptyMoreThan50() {
        return used.get() >= threshold;
    }

}
