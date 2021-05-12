package com.aj.shardingjdbc.news.controller;

import com.aj.shardingjdbc.news.domain.NewsVisit;
import com.aj.shardingjdbc.news.service.NewsVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class NewsVisitController {

    @Autowired
    private NewsVisitService newsVisitService;

    @GetMapping(value = "/add")
    public String addNewsVisit(@RequestParam(value = "time", required = false) Long time) {
        NewsVisit newsVisit = new NewsVisit();
        if (time == null) {
            newsVisit.setCreateTime(System.currentTimeMillis() / 1000);
        } else {
            newsVisit.setCreateTime(time);
        }
        newsVisit.setIp("127.0.0.1");
        newsVisit.setNewsId(1L);
        newsVisit.setProvince("重庆");
        newsVisit.setUserId(1L);
        newsVisitService.addNewsVisit(newsVisit);
        return "success";
    }


    @GetMapping(value = "/get")
    public String addNewsVisit() {
        List<NewsVisit> list = newsVisitService.getNewsVisit();
        System.out.println("size=" + list.size());
        System.out.println(list);
        return "success";
    }

    @GetMapping(value = "/page")
    public String addNewsVisit(@RequestParam(value = "offset") Integer offset,
                               @RequestParam(value = "limit") Integer limit) {
        List<NewsVisit> list = newsVisitService.page(offset, limit);
        System.out.println("size=" + list.size());
        System.out.println(list);
        return "success";
    }

}
