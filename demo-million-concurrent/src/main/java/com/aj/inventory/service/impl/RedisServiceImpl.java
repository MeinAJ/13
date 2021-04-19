package com.aj.inventory.service.impl;

import com.aj.inventory.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private Jedis jedis;

    @Override
    public void set(String key, String value) {
        jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }


}
