package com.aj.service.service;

import com.aj.news.api.domain.News;
import com.aj.service.api.FeignNewsService;
import com.aj.service.api.FeignUserService;
import com.aj.user.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private FeignNewsService feignNewsService;

    @Autowired
    private FeignUserService feignUserService;

    @Override
    public void test() {
        feignNewsService.addNews(new News());
        feignUserService.addUser(new User());
    }
}
