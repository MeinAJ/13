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

    public void upload(double lat, double lng, String province, String name) throws Exception {
        worker.put(province, new LatLng(lat, lng, name));
    }

}
