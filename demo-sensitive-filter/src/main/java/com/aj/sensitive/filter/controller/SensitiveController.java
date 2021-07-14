/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.filter.controller;

import com.aj.sensitive.filter.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author An Jun
 * @date 2021-04-21
 */
@RestController
@RequestMapping(value = "/api/v1/sensitive")
public class SensitiveController {

    @Autowired
    private SensitiveService sensitiveService;

    @GetMapping(value = "/check")
    public String checkComment(@RequestParam(value = "word") String comment) {
        return sensitiveService.checkComment(comment);
    }

}
