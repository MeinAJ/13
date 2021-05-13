package com.aj.service.services;

import com.aj.service.mapper.NewsUserMapper;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service(value = "newsUserServiceCancel")
@RequestMapping(value = "/api/v1/newsUser/cancel")
public class NewsUserServiceCancel implements NewsUserApi, CompensableContextAware {

    private CompensableContext compensableContext;

    @Autowired
    private NewsUserMapper newsUserMapper;

    @Override
    @Transactional
    public void addNewsUser() {
        Long id = (Long) compensableContext.getVariable("id");
        newsUserMapper.deleteNewsUser(id);
        System.out.println("cancel新闻用户,id=" + id);
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        System.out.println("NewsUserServiceCancel#setCompensableContext");
        this.compensableContext = compensableContext;
    }
}
