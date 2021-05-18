/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.distributed.id.snowflake.plus;

import com.aj.distributed.id.init.WorkerInfo;
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

    private final int capacity = 5;

    private final Long[] ringBuffer = new Long[capacity];

    private final Boolean[] ringBufferFlag = new Boolean[capacity];

    private AtomicInteger cursor = new AtomicInteger(-1);

    private AtomicInteger tail = new AtomicInteger(0);

    private final ReentrantLock outOfIndexLock = new ReentrantLock();

    private final AtomicLong lastSecond = new AtomicLong(System.currentTimeMillis() / 1000 - 1621267200L);

    private void init() {
        long lastTimeStamp = lastSecond.getAndIncrement();
        System.out.println("lastTimeStamp=" + lastTimeStamp);

        long prefix = ((lastTimeStamp) << timestampLeftShift) | (workerInfo.getWorkerId() << workerIdShift);

        for (int index = 0; index < capacity; index++) {
            long id = prefix | (index + 1) & sequenceMask;
            ringBuffer[index] = id;
            ringBufferFlag[index] = true;
        }
    }


    public Long nextId() throws Exception {
        Long nextId = getPossiableNextId();
        if (nextId == null) {
            throw new Exception("没有获取到id");
        }
        return nextId;
    }

    private Long getPossiableNextId() throws Exception {
        int index = cursor.incrementAndGet();
        if (index >= 1024) {
            try {
                if (outOfIndexLock.tryLock()) {
                    //尝试获取锁成功
                    System.out.println("尝试获取锁成功");
                    cursor.set(-1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                outOfIndexLock.unlock();
            }
            return getPossiableNextId();
        }
        Long id = ringBuffer[index];
        if (!ringBufferFlag[index]) {
            throw new Exception("这个id是重复的");
        }
        ringBufferFlag[index] = false;
        return id;
    }

}
