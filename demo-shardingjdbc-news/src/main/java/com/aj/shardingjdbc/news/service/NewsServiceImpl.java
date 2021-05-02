package com.aj.shardingjdbc.news.service;

import com.aj.shardingjdbc.news.dao.NewsDao;
import com.aj.shardingjdbc.news.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public void addNews(News user) {
        newsDao.addNews(user);
    }

    @Override
    public List<News> getNews() {
        return newsDao.getNews();
    }
}
