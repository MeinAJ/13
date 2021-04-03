package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
//        ReentrantLock reentrantLock = new ReentrantLock();
//        reentrantLock.lock();
//
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("cehis");
//            }
//        });
//
//        new Thread(() -> {
//            System.out.println("线程1");
//            try {
//                cyclicBarrier.await();
//                Thread.sleep(1000);
//                System.out.println("xiancehng1");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//
//        new Thread(() -> {
//            System.out.println("线程2");
//            try {
//                cyclicBarrier.await();
//                Thread.sleep(2000);
//                System.out.println("xiancehng2");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        Semaphore semaphore = new Semaphore(2);


        semaphore.release();
        semaphore.release();
        System.out.println("w3ww");
    }


}
