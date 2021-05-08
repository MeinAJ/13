package com.aj.user.service;

import com.aj.user.api.api.UserApi;
import com.aj.user.api.domain.User;
import com.aj.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService implements UserApi {

    @Autowired
    private UserMapper userMapper;
    @Value(value = "#{server.port}")
    private String port;

    @Override
    public void addUser(User user) {
        System.out.println("port=" + port);
        userMapper.addUser(user);
    }

}
