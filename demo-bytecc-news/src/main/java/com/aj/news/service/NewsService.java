package com.aj.news.service;

import com.aj.news.api.api.NewsApi;
import com.aj.news.api.domain.News;
import com.aj.news.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsService implements NewsApi {

    @Autowired
    private NewsMapper newsMapper;

    @Value(value = "#{server.port}")
    private String port;

    @Override
    public void addNews(News news) {
        System.out.println("port=" + port);
        newsMapper.addNews(news);
    }
}
