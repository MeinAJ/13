package com.aj.mapper.user;

import com.aj.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void addUser(User user);
}
