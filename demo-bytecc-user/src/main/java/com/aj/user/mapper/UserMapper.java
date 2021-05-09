package com.aj.user.mapper;

import com.aj.user.api.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert(value = "insert into t_user(name)values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(User user);

    @Delete(value = "delete from t_user where id = #{id}")
    void deleteUser(@Param(value = "id") Long id);
}
