package com.aj.service.services;

import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service(value = "newsUserServiceConfirm")
@RequestMapping(value = "/api/v1/newsUser/confirm")
public class NewsUserServiceConfirm implements NewsUserApi, CompensableContextAware {

    private CompensableContext compensableContext;

    @Override
    @Transactional
    public void addNewsUser() {
        Long id = (Long) compensableContext.getVariable("id");
        System.out.println("confirm新闻用户,id=" + id);
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        System.out.println("NewsUserServiceConfirm#setCompensableContext");
        this.compensableContext = compensableContext;
    }
}
