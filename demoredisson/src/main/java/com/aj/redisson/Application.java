package com.aj.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://192.168.0.103:7001")
                .addNodeAddress("redis://192.168.0.103:7002")
                .addNodeAddress("redis://192.168.0.104:7003")
                .addNodeAddress("redis://192.168.0.104:7004")
                .addNodeAddress("redis://192.168.0.105:7005")
                .addNodeAddress("redis://192.168.0.105:7006")
                .setPassword("redis-pass");
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");

        lock.lock();

        lock.unlock();

        System.out.println("over....");
    }

}
