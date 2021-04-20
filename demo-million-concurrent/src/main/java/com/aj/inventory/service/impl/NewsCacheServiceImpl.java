package com.aj.inventory.service.impl;

import com.aj.inventory.cache.RebuildCacheProcessor;
import com.aj.inventory.dao.NewsMapper;
import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsCacheService;
import com.aj.inventory.service.NewsLocalCacheService;
import com.aj.inventory.service.RedisService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service("newsCacheService")
public class NewsCacheServiceImpl implements NewsCacheService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private RedisService redisService;

    private final String news_info_prefix = "news_info_";

    @Autowired
    private NewsLocalCacheService newsLocalCacheService;

    @Override
    public News getNewsInfoFromCache(Long id) {
        //从redis
        News newsInfo = getNewsInfoFromRedisCache(id);
        System.out.println("getNewsInfoFromRedisCache=" + newsInfo);
        if (newsInfo == null) {
            //从ehcache
            newsInfo = getNewsInfoFromLocalCache(id);
            System.out.println("getNewsInfoFromLocalCache=" + newsInfo);
        }
        if (newsInfo == null) {
            //从mysql
            newsInfo = newsMapper.getNewsInfo(id);
            System.out.println("getNewsInfo=" + newsInfo);
            if (newsInfo != null) {
                //发送到RebuildCacheProcessor,去重构缓存
                RebuildCacheProcessor.put(newsInfo);
            }
        }
        return newsInfo;
    }

    private News getNewsInfoFromLocalCache(Long id) {
        return newsLocalCacheService.getNewsInfoFromLocalCache(id);
    }

    @Override
    public News getNewsInfoFromRedisCache(Long id) {
        String s = redisService.get(news_info_prefix + id);
        return JSON.parseObject(s, News.class);
    }

    @Override
    public void setNewsInfo2RedisCache(News news) {
        redisService.set(news_info_prefix + news.getId(), JSON.toJSONString(news));
        System.out.println("setNewsInfo2RedisCache=" + news);
    }

    @Override
    public void setNewsInfo2LocalCache(News news) {
        newsLocalCacheService.setNewsInfo2LocalCache(news);
    }

}
