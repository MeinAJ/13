package com.aj.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.aj.user.api.domain.User;

@Mapper
public interface UserMapper {

    @Insert(value = "insert into t_user(name)values(#{name})")
    void addUser(User user);

}
