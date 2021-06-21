/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rider.service;

import com.aj.rider.model.LatLng;
import com.aj.rider.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RiderService
 *
 * @author An Jun
 * @date 2021-06-21
 */
@Service(value = "riderService")
public class RiderService {

    @Autowired
    private Worker worker;

    public void upload(double lat, double lng, String province) {

        LatLng latLng = new LatLng();
        latLng.setLat(lat);
        latLng.setLng(lng);

        //todo 可以优化
        worker.getQueue(province).add(latLng);

        
    }


}
