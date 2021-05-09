package com.aj.service.services;

import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service(value = "newsUserServiceCancel")
@RequestMapping(value = "/api/v1/newsUser/cancel")
public class NewsUserServiceCancel implements NewsUserApi, CompensableContextAware {

    private CompensableContext compensableContext;

    @Override
    @Transactional
    public void addNewsUser() {
        System.out.println("cancel新闻用户");
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        System.out.println("NewsUserServiceCancel#setCompensableContext");
        this.compensableContext = compensableContext;
    }
}
