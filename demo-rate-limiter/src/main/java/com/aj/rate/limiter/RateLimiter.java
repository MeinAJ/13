/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rate.limiter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现100qps限流器
 *
 * @author An Jun
 * @date 2021-06-09
 */
public class RateLimiter {

    private int count = 0;

    private int qps = 100;

    private final ReentrantLock lock = new ReentrantLock(true);

    private long lastRefillTime = System.currentTimeMillis();

    RateLimiter() {
    }

    RateLimiter(int qps) {
        this.qps = qps;
    }

    private void reset() {
        count = 0;
        lastRefillTime = System.currentTimeMillis();
    }

    public boolean tryAcquire() {
        boolean result = true;
        int millis = 1000;
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            if ((++count) > qps) {
                //无法获取有效令牌
                result = false;
            }
            if ((now - lastRefillTime) > millis) {
                //超过刷新时间了,复位操作
                reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }

}
