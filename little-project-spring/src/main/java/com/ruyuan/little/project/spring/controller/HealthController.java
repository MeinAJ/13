package com.ruyuan.little.project.spring.controller;

import com.ruyuan.little.project.common.dto.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = "/dd")
    public CommonResponse health() {
        return CommonResponse.success();
    }

}
