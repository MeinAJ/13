package com.zhss.seckill.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import java.util.HashMap;

public class JedisManager {

    private final HashMap<String, Jedis> jedisMap = new HashMap<String, Jedis>();

    static class Singleton {

        static JedisManager instance = new JedisManager();

    }

    public static JedisManager getInstance() {
        return Singleton.instance;
    }

    public Jedis getJedis(String host, int port, String password) {
        String cacheKey = host + port;
        if (jedisMap.get(cacheKey) == null) {
            synchronized (this) {
                if (jedisMap.get(cacheKey) == null) {
                    JedisShardInfo shardInfo = new JedisShardInfo(host, port);
                    shardInfo.setPassword(password);
                    Jedis jedis = new Jedis(shardInfo);
                    jedisMap.put(cacheKey, jedis);
                }
            }
        }
        return jedisMap.get(cacheKey);
    }

}
