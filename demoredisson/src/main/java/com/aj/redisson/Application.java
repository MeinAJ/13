package com.aj.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonCountDownLatch;
import org.redisson.RedissonRedLock;
import org.redisson.RedissonSemaphore;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import java.util.concurrent.Semaphore;

@SuppressWarnings("ALL")
public class Application {

    public static void main(String[] args) throws InterruptedException {
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://192.168.0.103:7001")
//                .addNodeAddress("redis://192.168.0.103:7002")
//                .addNodeAddress("redis://192.168.0.104:7003")
//                .addNodeAddress("redis://192.168.0.104:7004")
//                .addNodeAddress("redis://192.168.0.105:7005")
//                .addNodeAddress("redis://192.168.0.105:7006")
//                .setPassword("redis-pass");
//        RedissonClient redisson = Redisson.create(config);

        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.2.52:6379");
        final RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");
        lock.lock();
        lock.unlock();
//
//        boolean b = lock.tryLock();
//        b = lock.tryLock(10, TimeUnit.SECONDS);
//        b = lock.tryLock(10, 30, TimeUnit.SECONDS);


//        RLock lock1 = redisson.getLock("anyLock1");
//        RLock lock2 = redisson.getLock("anyLock2");
//        RLock lock3 = redisson.getLock("anyLock3");
//        RedissonMultiLock multiLock = new RedissonMultiLock(lock1, lock2, lock3);
//        System.out.println("time=" + System.currentTimeMillis() / 1000);
//        System.out.println("开始获取锁");
//        multiLock.lock();
//        System.out.println("获取锁成功");
//        System.out.println("time=" + System.currentTimeMillis() / 1000);
//        multiLock.unlock();

//        RLock lock1 = redisson.getLock("anyLock1");
//        RLock lock2 = redisson.getLock("anyLock2");
//        RLock lock3 = redisson.getLock("anyLock3");
//        RedissonRedLock redissonRedLock = new RedissonRedLock(lock1, lock2, lock3);
//        redissonRedLock.lock();
//        redissonRedLock.unlock();

//        RCountDownLatch latch = redisson.getCountDownLatch("anyCountDownLatch");
//        latch.trySetCount(3);
//
//        latch.countDown();
//        latch.countDown();
//        latch.countDown();
//
//        latch.await();

        RSemaphore semaphore = redisson.getSemaphore("anySemaphore");
        semaphore.trySetPermits(3);

        semaphore.acquire();
        semaphore.release();

        semaphore.acquire();
        semaphore.release();

        semaphore.acquire();
        semaphore.release();

//        RLock fairLock = redisson.getFairLock("anyLock");
//        fairLock.lock();
//        fairLock.unlock();
//
//        RReadWriteLock readWriteLock = redisson.getReadWriteLock("anyLock");
//        RLock readLock = readWriteLock.readLock();
//        readLock.lock();
//        readLock.unlock();
//
//        RLock writeLock = readWriteLock.writeLock();
//        writeLock.lock();
//        writeLock.unlock();
//
//        System.out.println("over");
    }

}
