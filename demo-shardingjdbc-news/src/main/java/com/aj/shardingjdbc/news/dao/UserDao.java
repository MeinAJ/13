package com.aj.shardingjdbc.news.dao;

import com.aj.shardingjdbc.news.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    void addUser(User user);

    List<User> getUser();
}
