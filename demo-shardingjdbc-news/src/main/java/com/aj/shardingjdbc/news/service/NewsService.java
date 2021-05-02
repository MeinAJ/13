package com.aj.shardingjdbc.news.service;

import com.aj.shardingjdbc.news.domain.News;

import java.util.List;

public interface NewsService {

    void addNews(News news);

    List<News> getNews();

}
