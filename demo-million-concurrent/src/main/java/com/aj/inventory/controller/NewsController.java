package com.aj.inventory.controller;

import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/getNewsInfo")
    public News getUserInfo(@RequestParam(value = "newsId") Long newsId) {
        return newsService.getNewsInfo(newsId);
    }

}
