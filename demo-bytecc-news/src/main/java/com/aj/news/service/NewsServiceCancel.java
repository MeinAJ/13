package com.aj.news.service;

import com.aj.news.api.api.NewsApi;
import com.aj.news.api.domain.News;
import com.aj.news.mapper.NewsMapper;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service(value = "newsServiceCancel")
@RequestMapping("/api/v1/news/cancel")
public class NewsServiceCancel implements NewsApi, CompensableContextAware {

    @Autowired
    private NewsMapper newsMapper;

    private CompensableContext compensableContext;

    @Override
    @Transactional
    public void addNews(News news) {
        Long id = (Long) compensableContext.getVariable("id");
        newsMapper.deleteNews(id);
        System.out.println("删除刚才新建的新闻，id=" + id);
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        this.compensableContext = compensableContext;
    }
}
