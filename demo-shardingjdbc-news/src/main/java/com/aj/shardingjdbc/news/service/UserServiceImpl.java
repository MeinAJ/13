package com.aj.shardingjdbc.news.service;

import com.aj.shardingjdbc.news.dao.UserDao;
import com.aj.shardingjdbc.news.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getUser() {
        return userDao.getUser();
    }
}
