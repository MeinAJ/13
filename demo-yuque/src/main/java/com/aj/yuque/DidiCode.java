/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.yuque;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.开启10个线程，分别做一个复杂的计算操作，得到一个随机的值，然后最终10个线程做一个类加的值。
 *
 * @author An Jun
 * @date 2021-06-28
 */
public class DidiCode {

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random(25);
        AtomicInteger data = new AtomicInteger(0);
        int cycle = 10;
        CountDownLatch countDownLatch = new CountDownLatch(cycle);
        for (int i = 0; i < cycle; i++) {
            new Task(rand, data, countDownLatch).start();
        }
        countDownLatch.await();
        System.out.println("result=" + data.get());
    }

    static class Task extends Thread {

        private final Random rand;

        private final AtomicInteger data;

        private final CountDownLatch countDownLatch;

        Task(Random rand, AtomicInteger data, CountDownLatch countDownLatch) {
            this.rand = rand;
            this.data = data;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            int i = rand.nextInt(100);
            int expect = data.get();
            while (!data.compareAndSet(expect, expect + i)) {
                //do nothing
            }
            countDownLatch.countDown();
        }

    }

}
