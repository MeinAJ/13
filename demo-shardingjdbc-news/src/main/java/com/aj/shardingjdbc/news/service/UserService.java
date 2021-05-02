package com.aj.shardingjdbc.news.service;

import com.aj.shardingjdbc.news.domain.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getUser();

}
