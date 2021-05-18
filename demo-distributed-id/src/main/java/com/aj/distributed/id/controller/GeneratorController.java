/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.distributed.id.controller;

import com.aj.distributed.id.snowflake.plus.CachedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GeneraterController
 *
 * @author An Jun
 * @date 2021-05-18
 */
@RestController
@RequestMapping(value = "/api/v1/id")
public class GeneratorController {

    @Autowired
    private CachedGenerator cachedGenerator;

    @RequestMapping(value = "/generate")
    public Long generate() throws Exception {
        Long id = cachedGenerator.nextId();
        System.out.println(id);
        return id;
    }

}
