package com.aj.shardingjdbc.news.service;

import com.aj.shardingjdbc.news.dao.NewsVisitDao;
import com.aj.shardingjdbc.news.domain.NewsVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service(value = "newsVisitService")
public class NewsVisitServiceImpl implements NewsVisitService {

    @Autowired
    private NewsVisitDao newsVisitDao;

    @Override
    public void addNewsVisit(NewsVisit newsVisit) {
        newsVisitDao.addNewsVisit(newsVisit);
    }

    @Override
    public List<NewsVisit> getNewsVisit() {
        return newsVisitDao.getNewsVisit(1619930665L);
    }

    @Override
    public List<NewsVisit> page(Integer offset, Integer limit) {
        return newsVisitDao.page(offset, limit);
    }

}
