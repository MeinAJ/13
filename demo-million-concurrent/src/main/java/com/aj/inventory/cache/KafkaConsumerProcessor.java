/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.inventory.cache;

import com.aj.inventory.context.SpringContext;
import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsCacheService;
import com.alibaba.fastjson.JSON;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * RebuildCacheProcessor
 *
 * @author An Jun
 * @date 2021-04-20
 */
@SuppressWarnings("ALL")
public class KafkaConsumerProcessor implements Runnable {

    private static final ArrayBlockingQueue<News> QUEUE = new ArrayBlockingQueue<>(1000);
    private KafkaStream kafkaStream;
    private NewsCacheService newsCacheService;
    private CuratorFramework curatorClient;

    public KafkaConsumerProcessor(KafkaStream kafkaStream) {
        this.kafkaStream = kafkaStream;
        this.newsCacheService = (NewsCacheService) SpringContext.getApplicationContext().getBean("newsCacheService");
        this.curatorClient = (CuratorFramework) SpringContext.getApplicationContext().getBean("curatorClient");
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
        while (it.hasNext()) {
            try {
                String message = new String(it.next().message());
                if (!StringUtils.isEmpty(message)) {
                    News updateNews = JSON.parseObject(message, News.class);
                    System.out.println("updateNews=" + JSON.toJSONString(updateNews));
                    InterProcessMutex lock = new InterProcessMutex(curatorClient, "/news/lock2/" + updateNews.getId());
                    try {
                        System.out.println("消费kafka后,加锁");
                        lock.acquire();
                        News oldNews = newsCacheService.getNewsInfoFromRedisCache(updateNews.getId());
                        if (oldNews != null && oldNews.getId() != null) {
                            System.out.println("oldNews=" + JSON.toJSONString(oldNews));
                            if (oldNews.getUpdateTime() != null && updateNews.getUpdateTime() <= oldNews.getUpdateTime()) {
                                continue;
                            }
                        }
                        newsCacheService.setNewsInfo2LocalCache(updateNews);
                        System.out.println("消费kafka后,存入本地缓存");
                        newsCacheService.setNewsInfo2RedisCache(updateNews);
                        System.out.println("消费kafka后,存入redis缓存");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.release();
                        System.out.println("消费kafka后,释放锁");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
