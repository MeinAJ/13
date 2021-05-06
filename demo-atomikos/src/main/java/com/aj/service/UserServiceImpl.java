package com.aj.service;

import com.aj.domain.News;
import com.aj.domain.User;
import com.aj.mapper.news.NewsMapper;
import com.aj.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    @Transactional(transactionManager = "xatx", rollbackFor = Exception.class)
    public void addUser(User user) {
        userMapper.addUser(new User());
        newsMapper.addNews(new News());
    }

}
