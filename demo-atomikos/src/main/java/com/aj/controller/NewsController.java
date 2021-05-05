package com.aj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class NewsController {



    @GetMapping(value = "/addNews")
    public String addNews(){

        return "success";
    }

}
