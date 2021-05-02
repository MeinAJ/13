package com.aj.shardingjdbc.news.controller;

import com.aj.shardingjdbc.news.domain.News;
import com.aj.shardingjdbc.news.domain.User;
import com.aj.shardingjdbc.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/add")
    public String addNewsVisit(@RequestParam(value = "time", required = false) Long time) {
        News news = new News();
        news.setTitle("aj");
        news.setContent("aj");
        newsService.addNews(news);
        return "success";
    }


    @GetMapping(value = "/get")
    public String addNewsVisit() {
        List<News> list = newsService.getNews();
        System.out.println("size=" + list.size());
        System.out.println(list);
        return "success";
    }

}
