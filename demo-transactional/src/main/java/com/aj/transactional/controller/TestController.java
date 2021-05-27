/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.transactional.controller;

import com.aj.transactional.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author An Jun
 * @date 2021-04-21
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/test")
    public String test() {
        testService.test();
        return "success";
    }

}
