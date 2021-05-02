package com.aj.shardingjdbc.news.service;

import com.aj.shardingjdbc.news.domain.NewsVisit;

import java.util.List;

public interface NewsVisitService {

    void addNewsVisit(NewsVisit newsVisit);

    List<NewsVisit> getNewsVisit();

}
