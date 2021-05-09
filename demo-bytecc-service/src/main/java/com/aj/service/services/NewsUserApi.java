package com.aj.service.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface NewsUserApi {

    @GetMapping("/test")
    void addNewsUser();

}
