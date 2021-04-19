package com.aj.inventory.service.impl;

import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsCacheService;
import com.aj.inventory.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsCacheService newsCacheService;

    @Override
    public News getNewsInfo(Long id) {
        return newsCacheService.getNewsInfoFromCache(id);
    }

}
