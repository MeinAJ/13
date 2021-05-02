package com.aj.shardingjdbc.news.dao;

import com.aj.shardingjdbc.news.domain.NewsVisit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsVisitDao {
    void addNewsVisit(NewsVisit newsVisit);

    List<NewsVisit> getNewsVisit(Long createTime);
}
