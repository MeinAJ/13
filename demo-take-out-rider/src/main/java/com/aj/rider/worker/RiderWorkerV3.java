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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Worker
 *
 * @author An Jun
 * @date 2021-06-21
 */
@SuppressWarnings("ALL")
@Service(value = "riderWorkerV2")
public class RiderWorkerV3 {

    @Autowired
    private RedissonClient redissonClient;

    private final static LinkedBlockingQueue<LatLng> QUEUE = new LinkedBlockingQueue<>();

    private final static int THREADS = Runtime.getRuntime().availableProcessors() * 2;

    private final static int SIZE = 1000;

    private final static long MAX_WAIT_MILLIS = 1000;

    private final static int MAX_BATCH_TASK_WAIT_SIZE = 10;

    private static ExecutorService[] executorServices;

    public RiderWorkerV3() {
        initThreadExecutors();
        init();
    }

    private void initThreadExecutors() {
        int threads = THREADS;
        AtomicInteger threadId = new AtomicInteger(0);
        for (int i = 0; i < threads; i++) {
            executorServices[i] = new ThreadPoolExecutor(
                    1,
                    1,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(MAX_BATCH_TASK_WAIT_SIZE), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "batch-task-thread-" + threadId.incrementAndGet());
                }
            }, new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    //直接抛弃
                }
            });
        }
    }

    private ExecutorService getOneExecutorService(String province) {
        int h = 0;
        h = (province == null) ? 0 : (h = province.hashCode()) ^ (h >>> 16);
        h = h & (THREADS - 1);
        return executorServices[h];
    }

    private void init() {
        new Thread(() -> {
            int count = 0;
            Map<String, List<LatLng>> map = new HashMap<>();
            while (true) {
                try {
                    LatLng data;
                    long beginTime = System.currentTimeMillis();
                    //达到1000个数据时或者等待时间大于了1000ms时,就要批次保存了
                    while (count++ < SIZE
                            && (data = QUEUE.poll(MAX_WAIT_MILLIS, TimeUnit.MILLISECONDS)) != null
                            && System.currentTimeMillis() - beginTime <= MAX_WAIT_MILLIS) {
                        String province = data.getProvince();
                        List<LatLng> dataList = map.get(province);
                        if (CollectionUtils.isEmpty(dataList)) {
                            dataList = new ArrayList<>();
                            map.put(province, dataList);
                        }
                        dataList.add(data);
                    }
                    Set<String> provinceSet = map.keySet();
                    for (String province : provinceSet) {
                        List<LatLng> datalist = map.get(province);
                        //根据province进行hash,分配给特定的线程处理,这样就不会出现同一个地区的数据,
                        //防止了因为线程的执行无序导致同一个地区的数据不安全
                        getOneExecutorService(province).execute(new BatchTask(datalist, province));
                    }
                    //复位
                    count = 0;
                    map.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void put(String province, LatLng latLng) throws Exception {
        //等待0s,相当于满了后,直接抛弃掉数据
        QUEUE.offer(latLng, 0, TimeUnit.MILLISECONDS);
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
                    data[i] = new GeoEntry(latLng.getLat(), latLng.getLng(), latLng.getName());
                }
                System.out.println(JSON.toJSONString(data));
                place.add(data);
                dataList = null;
            }
        }

    }

}
