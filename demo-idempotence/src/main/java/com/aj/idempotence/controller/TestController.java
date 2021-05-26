/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.controller;

import com.aj.idempotence.service.OrderService;
import com.aj.idempotence.utils.SnowFlakeUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * TestController
 *
 * @author An Jun
 * @date 2021-04-30
 */
@RequestMapping("/api/v1")
@RestController
public class TestController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/check")
    public String test(@RequestParam(value = "token") String token) {
        orderService.createOrder(token);
        return "success";
    }

    @GetMapping(value = "/token")
    public String getToken() {
        String id = SnowFlakeUtil.getId();
        System.out.println("生成的雪花id=" + id);
        redissonClient.getBucket(id).set(System.currentTimeMillis(), 20, TimeUnit.SECONDS);
        System.out.println("雪花id放入redis中,time=" + LocalDateTime.now());
        return id;
    }

}
