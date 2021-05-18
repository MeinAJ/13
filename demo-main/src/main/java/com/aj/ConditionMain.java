/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionMain
 *
 * @author An Jun
 * @date 2021-05-18
 */
@SuppressWarnings("ALL")
public class ConditionMain {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    queue.take();
//                    System.out.println("执行完了");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    threadSleep(5000);
//                    queue.put("11");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


        new Thread() {
            @Override
            public void run() {
                System.out.println("thread1");
                threadSleep(2000);
                try {
                    lock.lock();
                    condition.signal();
                    System.out.println("signal");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("thread2");
                try {
                    lock.lock();
                    condition.await();
                    threadSleep(1000);
                    System.out.println("await结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
    }

    public static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
