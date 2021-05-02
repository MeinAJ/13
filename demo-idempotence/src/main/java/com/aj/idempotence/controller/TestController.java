/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.controller;

import com.aj.idempotence.annotations.CheckIdempotence;
import com.aj.idempotence.utils.SnowFlakeUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/check")
    @CheckIdempotence
    public String test(@RequestHeader(value = "token") String token) {
        System.out.println("有效token=" + token);
        return "success";
    }

    @GetMapping(value = "/token")
    public String getToken() {
        String id = SnowFlakeUtil.getId();
        System.out.println("生成的雪花id=" + id);
        redissonClient.getBucket(id).set(System.currentTimeMillis(), 1111, TimeUnit.SECONDS);
        System.out.println("雪花id放入redis中,time=" + LocalDateTime.now());
        return id;
    }

}