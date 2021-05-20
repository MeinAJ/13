/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.controller;

import com.aj.distributed.id.snowflake.plus.CachedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GeneraterController
 *
 * @author An Jun
 * @date 2021-05-18
 */
@RestController
@RequestMapping(value = "/api/v1/id")
public class SensitiveController {

    @Autowired
    private CachedGenerator cachedGenerator;

    private final AtomicInteger count = new AtomicInteger(0);

    @RequestMapping(value = "/generate")
    public Long generate() throws Exception {
        Long id = cachedGenerator.nextId();
        if (id == null) {
            throw new Exception("异常");
        }
        System.out.println(id);
        System.out.println("id=" + id + ",sort=" + count.incrementAndGet());
        return id;
    }

}
