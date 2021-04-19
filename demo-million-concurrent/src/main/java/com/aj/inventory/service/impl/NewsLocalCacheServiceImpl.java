package com.aj.inventory.service.impl;

import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsLocalCacheService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service("newsLocalCacheService")
@CacheConfig(cacheNames = "GoodsType")
public class NewsLocalCacheServiceImpl implements NewsLocalCacheService {

    @CachePut(key = "'news_info_'+#news.getId()")
    public News setNewsInfo2LocalCache(News news) {
        System.out.println("setNewsInfo2LocalCache=" + news);
        return news;
    }

    @Cacheable(key = "'news_info_'+#id")
    public News getNewsInfoFromLocalCache(Long id) {
        return null;
    }

}
