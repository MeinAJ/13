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

    @Override
    public void run() {
        try {
            News updateNews;
            while ((updateNews = take()) != null) {
                //todo 去更新redis和ehcache数据
                NewsCacheService newsCacheService = (NewsCacheService) SpringContext.getApplicationContext().getBean("newsCacheService");
                CuratorFramework curatorClient = (CuratorFramework) SpringContext.getApplicationContext().getBean("curatorClient");
                InterProcessMutex lock = new InterProcessMutex(curatorClient, "/news/cache/lock/" + updateNews.getId());
                lock.acquire();

                newsCacheService.get

                lock.release();
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
