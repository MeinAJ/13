package com.aj.sensitive.filter.config;

import com.aj.sensitive.filter.lock.DistributionLock;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://192.168.0.103:7001")
//                .addNodeAddress("redis://192.168.0.103:7002")
//                .addNodeAddress("redis://192.168.0.104:7003")
//                .addNodeAddress("redis://192.168.0.104:7004")
//                .addNodeAddress("redis://192.168.0.105:7005")
//                .addNodeAddress("redis://192.168.0.105:7006")
//                .setPassword("redis-pass");
//        return Redisson.create(config);
//    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.2.52:6379");
        return Redisson.create(config);
    }

    @Bean
    public DistributionLock distributionLock(@Qualifier(value = "redissonClient") RedissonClient redissonClient) {
        return new DistributionLock(redissonClient);
    }

}
