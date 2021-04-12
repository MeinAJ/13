package com.aj.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Application {

    public static void main(String[] args) {
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
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");
        lock.lock();
        lock.unlock();

        RLock fairLock = redisson.getFairLock("anyLock");
        fairLock.lock();
        fairLock.unlock();

        RReadWriteLock readWriteLock = redisson.getReadWriteLock("anyLock");
        RLock readLock = readWriteLock.readLock();
        readLock.lock();
        readLock.unlock();

        RLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        writeLock.unlock();

        System.out.println("over");
    }

}
