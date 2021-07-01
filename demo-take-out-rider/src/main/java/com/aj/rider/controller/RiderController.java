/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rider.controller;

import com.aj.rider.model.LatLng;
import com.aj.rider.worker.RiderWorkerV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RiderController {

//    @Autowired
//    private RiderWorker riderWorker;

    @Autowired
    private RiderWorkerV3 riderWorkerV3;

    @RequestMapping("/rider")
    public void upload(@RequestParam(value = "lat") double lat,
                       @RequestParam(value = "lng") double lng,
                       @RequestParam(value = "province") String province,
                       @RequestParam(value = "name") String name) throws Exception {
        System.out.println(lat + lng + province + name);
//        riderWorker.upload(lat, lng, province, name);
        riderWorkerV3.put(new LatLng(lat, lng, province, name));
    }

}
