package com.aj.service;

import com.aj.domain.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {


    @Override
    @Transactional(rollbackFor = Exception.class,transactionManager = "xatx")
    public void addNews(News news) {

    }
}
