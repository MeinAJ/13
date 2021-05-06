package com.aj.mapper.user;

import com.aj.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert(value = "insert into t_user(name)values(#{name})")
    void addUser(User user);

}
