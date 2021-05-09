package com.aj.service.services;

import com.aj.news.api.domain.News;
import com.aj.service.api.FeignNewsService;
import com.aj.service.api.FeignUserService;
import com.aj.service.domain.NewsUser;
import com.aj.service.mapper.NewsUserMapper;
import com.aj.user.api.domain.User;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Compensable(interfaceClass = NewsUserApi.class, cancellableKey = "newsUserServiceCancel", confirmableKey = "newsUserServiceConfirm")
public class NewsUserService implements NewsUserApi, CompensableContextAware {

    private CompensableContext compensableContext;

    @Autowired
    private NewsUserMapper newsUserMapper;

    @Autowired
    private FeignUserService userService;

    @Autowired
    private FeignNewsService newsService;

    @Override
    @Transactional
    public void addNewsUser() {
        newsUserMapper.addNewsUser(new NewsUser());
        System.out.println("创建新闻用户");
        userService.addUser(new User());
        newsService.addNews(new News());
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        System.out.println("NewsUserService#setCompensableContext");
        this.compensableContext = compensableContext;
    }
}
