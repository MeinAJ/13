package com.aj.news.service;

import com.aj.news.api.api.NewsApi;
import com.aj.news.api.domain.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service(value = "newsServiceConfirm")
@RequestMapping("/api/v1/news/confirm")
public class NewsServiceConfirm implements NewsApi {

    @Override
    @Transactional
    public void addNews(News news) {
        System.out.println("confirm新闻");
    }

}
