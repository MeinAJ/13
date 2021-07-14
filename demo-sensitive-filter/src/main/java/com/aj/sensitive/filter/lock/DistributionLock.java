package com.aj.sensitive.filter.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class DistributionLock {

    private final RLock lock;

    public DistributionLock(RedissonClient redissonClient) {
        lock = redissonClient.getLock("sensitive-lock");
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void unLock() {
        lock.unlock();
    }
}
