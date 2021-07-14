/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueueMain
 *
 * @author An Jun
 * @date 2021-07-13
 */
public class ArrayBlockingQueueMain {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    queue.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    queue.put("4");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        queue.put("1");
        queue.put("2");




        String take = queue.take();

        System.out.println(take);
    }

}
