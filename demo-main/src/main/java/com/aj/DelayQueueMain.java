/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueueMain
 *
 * @author An Jun
 * @date 2021-07-09
 */
public class DelayQueueMain {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        System.out.println("开始时间=" + System.currentTimeMillis() / 1000);
        executor.scheduleWithFixedDelay(() -> System.out.println("1打印,执行时间=" + System.currentTimeMillis() / 1000), 2, 1, TimeUnit.SECONDS);
    }

}
