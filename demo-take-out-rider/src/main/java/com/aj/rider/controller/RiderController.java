/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rider.controller;

import com.aj.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RiderController {

    @Autowired
    private RiderService riderService;

    @RequestMapping("/rider")
    public void upload(@RequestParam(value = "lat") double lat,
                       @RequestParam(value = "lng") double lng,
                       @RequestParam(value = "province") String province,
                       @RequestParam(value = "name") String name) throws Exception {
        riderService.upload(lat, lng, province, name);
    }

}
