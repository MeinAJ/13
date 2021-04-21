/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.inventory.cache;

import com.aj.inventory.context.SpringContext;
import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsCacheService;
import com.alibaba.fastjson.JSON;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * KafkaConsumer
 *
 * @author An Jun
 * @date 2021-04-20
 */
@SuppressWarnings("ALL")
public class KafkaConsumerThread implements Runnable {

    private final ConsumerConnector consumerConnector;

    public KafkaConsumerThread() {
        this.consumerConnector = Consumer.createJavaConsumerConnector(createConsumerConfig());
    }

    @Override
    public void run() {
        String topic = "news-update";
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        for (KafkaStream stream : streams) {
            new Thread(new KafkaConsumerProcessor(stream)).start();
        }
    }

    private ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.0.207:2181,192.168.0.208:2181,192.168.0.209:2181");
        props.put("group.id", "eshop-cache-group");
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    public class KafkaConsumerProcessor implements Runnable {

        private final ArrayBlockingQueue<News> queue = new ArrayBlockingQueue<>(1000);

        private KafkaStream kafkaStream;

        private final NewsCacheService newsCacheService;

        private final CuratorFramework curatorClient;

        public KafkaConsumerProcessor(KafkaStream kafkaStream) {
            this.kafkaStream = kafkaStream;
            this.newsCacheService = SpringContext.getApplicationContext().getBean(NewsCacheService.class);
            this.curatorClient = SpringContext.getApplicationContext().getBean(CuratorFramework.class);
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

}
