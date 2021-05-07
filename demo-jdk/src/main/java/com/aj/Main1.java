/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main1
 *
 * @author An Jun
 * @date 2021-05-07
 */
public class Main1 {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(5);
        map.put("1", "1");
        map.get("1");
        map.remove("1");

        Integer hashCode = "11111".hashCode();
        System.out.println("hashCode=" + hashCode);
        System.out.println("hashCode.byteValue()=" + hashCode.byteValue());
        hashCode = hashCode ^ (hashCode >>> 16);
        System.out.println("hashCode >>> 16=" + (hashCode >>> 16));
        System.out.println("hashCode >>> 16,byte=" + ((Integer) (hashCode >>> 16)).byteValue());

        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            int a = i % 8;
            System.out.println(a);
        }
        long time1 = System.currentTimeMillis();

        long time2 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            int a = 7 & i;
            System.out.println(a);
        }
        long time3 = System.currentTimeMillis();
        System.out.println(time1 - time);
        System.out.println(time3 - time2);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                10,
                20,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> new Thread(new ThreadGroup("test"), r)
        );
        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "," + System.currentTimeMillis());
        });
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }

}
