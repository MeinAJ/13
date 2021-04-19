package com.aj.inventory.service;

import com.aj.inventory.model.News;

public interface NewsCacheService {

    News getNewsInfoFromCache(Long id);

}
