/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rider.worker;

import com.aj.rider.model.LatLng;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Worker
 *
 * @author An Jun
 * @date 2021-06-21
 */
@Service(value = "worker")
public class Worker {

    private final ConcurrentHashMap<String /*province*/, ConcurrentLinkedQueue<LatLng>> map = new ConcurrentHashMap<>();

    public ConcurrentLinkedQueue<LatLng> getQueue(String province) {
        ConcurrentLinkedQueue<LatLng> queue = map.get(province);
        if (queue == null) {
            map.putIfAbsent(province, new ConcurrentLinkedQueue<>());
        }
        return map.get(province);
    }

}
