package com.aj.user.service;

import com.aj.user.api.api.UserApi;
import com.aj.user.api.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/confirm")
public class UserServiceConfirm implements UserApi {

    @Override
    @Transactional
    public void addUser(User user) {
        System.out.println("confirm用户");
    }

}
