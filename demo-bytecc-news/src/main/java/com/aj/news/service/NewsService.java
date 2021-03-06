package com.aj.news.service;

import com.aj.news.api.api.NewsApi;
import com.aj.news.api.domain.News;
import com.aj.news.mapper.NewsMapper;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Compensable(interfaceClass = NewsApi.class, confirmableKey = "newsServiceConfirm", cancellableKey = "newsServiceCancel")
public class NewsService implements NewsApi, CompensableContextAware {

    @Autowired
    private NewsMapper newsMapper;

    private CompensableContext compensableContext;

    @Override
    @Transactional
    public void addNews(@RequestBody News news) {
        newsMapper.addNews(news);
        compensableContext.setVariable("id", news.getId());
        System.out.println("创建新闻，id=" + news.getId());
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        this.compensableContext = compensableContext;
    }
}
