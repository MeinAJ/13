package com.aj.shardingjdbc.news.dao;

import com.aj.shardingjdbc.news.domain.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {
    void addNews(News user);

    List<News> getNews();
}
