/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.inventory.cache;

import com.aj.inventory.context.SpringContext;
import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsCacheService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * RebuildCacheProcessor
 *
 * @author An Jun
 * @date 2021-04-20
 */
public class RebuildCacheProcessor implements Runnable {

    private static final ArrayBlockingQueue<News> QUEUE = new ArrayBlockingQueue<>(1000);

    private final NewsCacheService newsCacheService;

    private final CuratorFramework curatorClient;

    public RebuildCacheProcessor() {
        this.newsCacheService = SpringContext.getApplicationContext().getBean(NewsCacheService.class);
        this.curatorClient = SpringContext.getApplicationContext().getBean(CuratorFramework.class);
    }

    @Override
    public void run() {
        try {
            while (true) {
                News updateNews = take();
                assert updateNews != null;
                InterProcessMutex lock = new InterProcessMutex(curatorClient, "/news/lock2/" + updateNews.getId());
                try {
                    lock.acquire();
                    News oldNews = newsCacheService.getNewsInfoFromRedisCache(updateNews.getId());
                    if (oldNews != null && oldNews.getId() != null) {
                        if (oldNews.getUpdateTime() != null && updateNews.getUpdateTime() <= oldNews.getUpdateTime()) {
                            continue;
                        }
                    }
                    newsCacheService.setNewsInfo2LocalCache(updateNews);
                    newsCacheService.setNewsInfo2RedisCache(updateNews);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void put(News news) {
        try {
            QUEUE.put(news);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static News take() {
        try {
            return QUEUE.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
