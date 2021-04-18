package com.aj.inventory.service.impl;

import com.aj.inventory.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }


}
