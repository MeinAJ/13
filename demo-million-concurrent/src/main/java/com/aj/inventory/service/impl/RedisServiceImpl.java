package com.aj.inventory.service.impl;

import com.aj.inventory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

//    @Resource
//    private Jedis jedis;

    @Autowired
    private JedisCluster jedisCluster;

//    @Override
//    public void set(String key, String value) {
//        jedis.set(key, value);
//    }
//
//    @Override
//    public String get(String key) {
//        return jedis.get(key);
//    }

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }
}
