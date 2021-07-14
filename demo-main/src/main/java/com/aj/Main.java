package com.aj;

import java.io.IOException;

@SuppressWarnings("ALL")
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

//        ReentrantLock lock = new ReentrantLock();
//        Condition condition = lock.newCondition();
//
//        ArrayBlockingQueue queue = new ArrayBlockingQueue<String>(100);
//
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("kaishi");
//                    Object take = queue.take();
//                    System.out.println(take);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread1.setName("queue.take");
//        thread1.start();
//
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    queue.put("111");
//                    System.out.println("queue.put(\"111\");");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.setName("queue.put");
//        thread.start();
//
//        while (true) {
//        }
//
    }
//
//    private static void test() {
//        byte[] array1 = new byte[1024 * 1024];
//        array1 = new byte[1024 * 1024];
//        array1 = new byte[1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[2 * 1024 * 1024];
//
//        byte[] array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
//
//        byte[] array3 = new byte[2 * 1024 * 1024];
//
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[128 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];
//
//        System.gc();
//        print();
//        stackOom();
//        heapOom();
//        metaspaceSize();
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//
//        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
//        queue.put(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("第一个开始执行");
//                sleep();
//                System.out.println("第一个执行完");
//            }
//        });
//
//        不阻塞, 放入不了, 直接返回false
//        queue.offer(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("第二个开始执行");
//                sleep();
//                System.out.println("第二个执行完");
//            }
//        });
//
//        Runnable take = queue.take();
//        take.run();
//        System.out.println("1");
//        queue.poll(5, TimeUnit.SECONDS);
//        System.out.println("2");
//        固定大小线程数, 当所有可用线程都在使用时, 全部放入阻塞链表中, 无限放入, 默认是Integer的最大值, 几乎放不满, 放满了那就要oom了
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                100,
//                100,
//                0L,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                0,
//                Integer.MAX_VALUE,
//                60L,
//                TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
//
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//    }
//
//    private static void printThreadName() {
//        System.out.println(Thread.currentThread().getName());
//    }
//
//    private static void printTime() {
//        System.out.println(LocalDateTime.now());
//    }
//
//    private static void printOver() {
//        System.out.println(Thread.currentThread().getName() + ",执行完");
//    }
//
//    private static void sleep() {
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//
//        }
//    }
//
//    private static void metaspaceSize() {
//        Main main = new Main();
//        Main main1 = new Main();
//
//        Class<? extends Main> aClass = main.getClass();
//        Class<? extends Main> aClass1 = main1.getClass();
//        System.out.println("1");
//    }
//
//    private static void heapOom() {
//        List<Main> list = new ArrayList<>();
//
//        while (true) {
//            list.add(new Main());
//        }
//    }
//
//    private static void stackOom() {
//        int i = 0;
//        System.out.println(i);
//        stackOom();
//    }

//    public static void print() throws InterruptedException {
//        List<Data> datas = new ArrayList<Data>();
//
//        for (int i = 0; i < 10000; i++) {
//            datas.add(new Data());
//        }
//        Thread.sleep(3600000);
//    }
//
//    static class Data {
//
//    }

}
