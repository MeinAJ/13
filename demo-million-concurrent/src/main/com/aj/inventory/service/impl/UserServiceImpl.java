package com.aj.inventory.service.impl;

import com.aj.inventory.dao.UserMapper;
import com.aj.inventory.model.User;
import com.aj.inventory.service.RedisService;
import com.aj.inventory.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public User getUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUserInfo() {
        redisService.set("cached_user", "{\"name\": \"zhangsan\", \"age\": 25}");
        String json = redisService.get("cached_user");
        JSONObject jsonObject = JSONObject.parseObject(json);

        User user = new User();
        user.setName(jsonObject.getString("name"));
        user.setAge(jsonObject.getInteger("age"));

        return user;
    }
}
