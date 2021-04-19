package com.aj.inventory.service;

import com.aj.inventory.model.News;

public interface NewsLocalCacheService {

    News setNewsInfo2LocalCache(News news);

    News getNewsInfoFromLocalCache(Long id);

}
