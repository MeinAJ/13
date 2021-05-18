package com.aj;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalMain
 *
 * @author An Jun
 * @date 2021-05-17
 */
@SuppressWarnings("ALL")
public class ThreadLocalMain {


    public static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    public static final InheritableThreadLocal<String> inheritableThreadLocal1 = new InheritableThreadLocal<>();

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static final ScheduledExecutorService threadPoolExecutor = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
//        inheritableThreadLocal.set("Inheritable hello");
//        inheritableThreadLocal1.set("Inheritable hello 1");
//        threadLocal.set("hello");
//        new Thread(() -> {
//            System.out.println(String.format("子线程可继承值：%s", inheritableThreadLocal.get()));
//            System.out.println(String.format("子线程可继承值1：%s", inheritableThreadLocal1.get()));
//            System.out.println(String.format("子线程值：%s", threadLocal.get()));
//            new Thread(() -> {
//                System.out.println(String.format("孙子线程可继承值：%s", inheritableThreadLocal.get()));
//                System.out.println(String.format("孙子线程可继承值1：%s", inheritableThreadLocal1.get()));
//                System.out.println(String.format("孙子线程值：%s", threadLocal.get()));
//            }).start();
//
//        }).start();

        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "," + System.currentTimeMillis() / 1000);
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

        System.out.println("1");
        System.out.println("2");

        System.out.println(2 >> 1);




    }

}
