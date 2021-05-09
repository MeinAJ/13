package com.aj.user.service;

import com.aj.user.api.api.UserApi;
import com.aj.user.api.domain.User;
import com.aj.user.mapper.UserMapper;
import org.bytesoft.bytetcc.supports.spring.aware.CompensableContextAware;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/cancel")
public class UserServiceCancel implements UserApi, CompensableContextAware {

    @Autowired
    private UserMapper userMapper;

    private CompensableContext compensableContext;

    @Override
    @Transactional
    public void addUser(User user) {
        Long id = (Long) compensableContext.getVariable("id");
        userMapper.deleteUser(id);
        System.out.println("删除了用户，id=" + id);
    }

    @Override
    public void setCompensableContext(CompensableContext compensableContext) {
        this.compensableContext = compensableContext;
    }
}
