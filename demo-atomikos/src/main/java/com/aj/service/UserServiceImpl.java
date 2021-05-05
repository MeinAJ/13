package com.aj.service;

import com.aj.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "xatx")
    public void addUser(User user) {

    }
}
