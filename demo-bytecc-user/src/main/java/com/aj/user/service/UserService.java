package com.aj.user.service;

import com.aj.user.api.api.UserApi;
import com.aj.user.api.domain.User;
import com.aj.user.mapper.UserMapper;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Compensable(interfaceClass = UserApi.class, confirmableKey = "userServiceConfirm", cancellableKey = "userServiceCancel")
public class UserService implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void addUser(@RequestBody User user) {
        userMapper.addUser(user);
    }

}
