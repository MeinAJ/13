/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rider.worker;

import com.aj.rider.model.LatLng;
import com.alibaba.fastjson.JSON;
import org.redisson.api.GeoEntry;
import org.redisson.api.RGeo;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Worker
 *
 * @author An Jun
 * @date 2021-06-21
 */
@Service(value = "riderWorker")
public class RiderWorker {

    @Autowired
    private RedissonClient redissonClient;

    private final static ConcurrentHashMap<String /*province*/,
            LinkedBlockingQueue<LatLng>/*queue*/> map = new ConcurrentHashMap<>();

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final static int cpu = Runtime.getRuntime().availableProcessors() * 2;

    private final static int size = 100;

    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            cpu,
            cpu,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public RiderWorker() {
        init();
    }

    private void init() {
        scheduler.scheduleWithFixedDelay(new Task(), 1000, 10000, TimeUnit.MILLISECONDS);
    }

    public void put(String province, LatLng latLng) throws Exception {
        if (executor.isShutdown()) {
            throw new Exception("无法消费");
        }
        LinkedBlockingQueue<LatLng> queue = map.get(province);
        if (queue == null) {
            queue = new LinkedBlockingQueue<>();
            map.putIfAbsent(province, queue);
        }
        queue.add(latLng);
    }

    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("开始执行任务，往redis中存入数据，" + System.currentTimeMillis() / 1000);
            ConcurrentHashMap.KeySetView<String,
                    LinkedBlockingQueue<LatLng>> provinceKeys = map.keySet();
            for (String province : provinceKeys) {
                LinkedBlockingQueue<LatLng> queue = map.get(province);
                if (queue != null && queue.size() > 0) {
                    List<LatLng> dataList = new ArrayList<>();
                    LatLng data;
                    int count = 0;
                    while ((data = queue.poll()) != null && count++ <= size) {
                        dataList.add(data);
                    }
                    if (!CollectionUtils.isEmpty(dataList)) {
                        executor.execute(new BatchTask(dataList, province));
                    }
                }
            }
        }
    }

    class BatchTask implements Runnable {

        List<LatLng> dataList;
        String province;

        BatchTask(List<LatLng> dataList, String province) {
            this.dataList = dataList;
            this.province = province;
        }

        @Override
        public void run() {
            if (!CollectionUtils.isEmpty(dataList)) {
                RGeo<Object> place = redissonClient.getGeo("place:" + province);
                GeoEntry[] data = new GeoEntry[dataList.size()];
                for (int i = 0; i < dataList.size(); i++) {
                    LatLng latLng = dataList.get(i);
                    GeoEntry geoEntry = new GeoEntry(latLng.getLat(), latLng.getLng(), latLng.getName());
                    data[i] = geoEntry;
                }
                System.out.println(JSON.toJSONString(data));
                place.add(data);
                dataList = null;
            }
        }
    }
}
