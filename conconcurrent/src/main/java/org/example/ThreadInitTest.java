package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class ThreadInitTest {

    public static void main(String[] args) throws InterruptedException {


        final Thread1 thread1 = new Thread1();
        thread1.start();
        thread1.wait();

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();

        LongAdder longAdder = new LongAdder();
        longAdder.longValue();
        new Thread() {
            @Override
            public void run() {
                System.out.println("1=" + System.currentTimeMillis() / 1000);
                System.out.println(111);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread1.interrupt();
            }
        }.start();


    }

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("222");
            synchronized (ThreadInitTest.class) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("2=" + System.currentTimeMillis() / 1000);
                e.printStackTrace();
            }
        }
    }
}
