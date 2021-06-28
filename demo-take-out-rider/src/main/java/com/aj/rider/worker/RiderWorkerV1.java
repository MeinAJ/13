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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * Worker
 *
 * @author An Jun
 * @date 2021-06-21
 */
@Service(value = "riderWorker")
public class RiderWorkerV1 {

    @Autowired
    private RedissonClient redissonClient;

    private final static ConcurrentHashMap<String /*province*/,
            LinkedBlockingQueue<LatLng>/*queue*/> MAP = new ConcurrentHashMap<>();

    private final static ScheduledExecutorService SCHEDULER = newScheduledThreadPool(1);

    private final static int CPU = Runtime.getRuntime().availableProcessors() * 2;

    private final static int SIZE = 1000;

    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            CPU,
            CPU,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), (ThreadFactory) Thread::new);

    public RiderWorkerV1() {
        init();
    }

    private void init() {
        SCHEDULER.scheduleWithFixedDelay(new Task(), 1000/*首次延时1s*/, 100 /*100ms执行一次*/, TimeUnit.MILLISECONDS);
    }

    public void put(String province, LatLng latLng) throws Exception {
        if (EXECUTOR.isShutdown()) {
            throw new Exception("无法消费");
        }
        LinkedBlockingQueue<LatLng> queue = MAP.get(province);
        if (queue == null) {
            queue = new LinkedBlockingQueue<>();
            MAP.putIfAbsent(province, queue);
        }
        queue.offer(latLng, 0, TimeUnit.MILLISECONDS);
    }

    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("开始执行任务，往redis中存入数据，" + System.currentTimeMillis() / 1000);
            ConcurrentHashMap.KeySetView<String,
                    LinkedBlockingQueue<LatLng>> provinceKeys = MAP.keySet();
            for (String province : provinceKeys) {
                LinkedBlockingQueue<LatLng> queue = MAP.get(province);
                if (queue != null && queue.size() > 0) {
                    List<LatLng> dataList = new ArrayList<>();
                    LatLng data;
                    int count = 0;
                    while ((data = queue.poll()) != null && count++ <= SIZE) {
                        dataList.add(data);
                    }
                    if (!CollectionUtils.isEmpty(dataList)) {
                        EXECUTOR.execute(new BatchTask(dataList, province));
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
