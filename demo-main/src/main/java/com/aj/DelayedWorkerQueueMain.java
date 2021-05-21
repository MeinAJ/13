/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

/**
 * DelayedWorkerQueueMain
 *
 * @author An Jun
 * @date 2021-05-21
 */
public class DelayedWorkerQueueMain {


    private static final ScheduledExecutorService THREAD_POOL_EXECUTOR = newSingleThreadScheduledExecutor();

    private final AtomicBoolean generating = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        THREAD_POOL_EXECUTOR.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() / 1000);
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

}
