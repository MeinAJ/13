package com.aj.rider.worker;

import org.redisson.api.GeoEntry;
import org.redisson.api.RGeo;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void upload(double lat, double lng, String province, String name) {
        RGeo<Object> place = redissonClient.getGeo("place:" + province);
        GeoEntry[] data = new GeoEntry[1];
        GeoEntry geoEntry = new GeoEntry(lat, lng, name);
        data[0] = geoEntry;
        place.add(data);
    }

}
