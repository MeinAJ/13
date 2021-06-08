package com.aj.redisson;

import org.redisson.Redisson;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import java.util.ArrayList;
import java.util.List;

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
//        final RedissonClient redisson = Redisson.create(config);
//        RBucket<Long> rate = redisson.getBucket("dd");
//        rate.set(1L, 60, TimeUnit.SECONDS);
//        Long aLong = rate.get();
//        System.out.println(aLong);
//
//        for (int i = 0; i < 65; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    String key = "filter:rate";
//                    RAtomicLong filter = redisson.getAtomicLong(key);
//                    filter.expire(1, TimeUnit.SECONDS);
//                    Long value = filter.incrementAndGet();
//                    if (value == null || value > 60) {
//                        //判定到已经失效
//                        RLock fairLock = redisson.getFairLock("filter:lock:" + key);
//                        if (fairLock.tryLock()) {
//                            filter.set(0);
//                            filter.expire(1, TimeUnit.SECONDS);
//                            value = filter.incrementAndGet();
//                            System.out.println("重新获取值=" + value);
//                        }
//                        System.out.println("没有获取到值");
//                    } else {
//                        System.out.println("当前值=" + value);
//                    }
//                }
//            }.start();
//        }


//        RLock lock = redisson.getLock("anyLock");
//        lock.lock();
//        System.out.println("加锁");
//        lock.unlock();
//        System.out.println("解锁");
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

//        RSemaphore semaphore = redisson.getSemaphore("anySemaphore");
//        semaphore.trySetPermits(3);
//
//        semaphore.acquire();
//        semaphore.release();
//
//        semaphore.acquire();
//        semaphore.release();
//
//        semaphore.acquire();
//        semaphore.release();

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
//        String s = "bloomfilter-";
//        RBloomFilter<Object> test = redisson.getBloomFilter(s);
//        test.tryInit(100000000L,0.03);
//        boolean add0 = test.add("1");
//        boolean add1 = test.add("2");
//        boolean add2 = test.add("3");
//        boolean add3 = test.add("4");
//        boolean add4 = test.add("5");
//
//        boolean contains = test.contains("1");
//        System.out.println(contains);
//        contains = test.contains("6");
//        System.out.println(contains);


    }


    {
        //基于lua脚本实现计数限流,1000ms,限流60个请求
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.2.52:6379");
        final RedissonClient redisson = Redisson.create(config);
        List<Object> keys = new ArrayList<Object>();
        keys.add("rate:key");
        Long count = redisson.getScript().evalSha(
                RScript.Mode.READ_WRITE,
                "6730ca2a89e6e2aee882ca44b0868874877a6690",
                RScript.ReturnType.INTEGER,
                keys,
                60,
                1000);
        System.out.println(count);
        /**
         * lua脚本如下:
         * ------------------------------------------------------------------------------------------
         * local count = tonumber(redis.call("incr", KEYS[1]))
         * if (count == 1) then
         *     redis.call("pexpire", KEYS[1], tonumber(ARGV[2]))
         * elseif (count > tonumber(ARGV[1])) then
         *     return -1
         * end
         * return cnt
         * ------------------------------------------------------------------------------------------
         *
         * 将lua脚本缓存:
         * ------------------------------------------------------------------------------------------
         * SCRIPT LOAD <lua脚本,如上>
         * ------------------------------------------------------------------------------------------
         */
    }

}
