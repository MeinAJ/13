package com.aj.service.api;

import com.aj.news.api.api.NewsApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "demo-news")
public interface FeignNewsService extends NewsApi {
}
