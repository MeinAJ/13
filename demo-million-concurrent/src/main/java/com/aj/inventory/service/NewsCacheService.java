package com.aj.inventory.service;

import com.aj.inventory.model.News;

public interface NewsCacheService {

    News getNewsInfoFromCache(Long id);

    void setNewsInfo2RedisCache(News news);

    void setNewsInfo2LocalCache(News news);

    News getNewsInfoFromRedisCache(Long id);

}
