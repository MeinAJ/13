package com.aj.service.controller;

import com.aj.service.service.TestService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/test")
    public String test(){
        testService.test();
        return "success";
    }

}
