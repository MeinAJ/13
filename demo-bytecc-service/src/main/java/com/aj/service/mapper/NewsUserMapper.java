package com.aj.service.mapper;

import com.aj.service.domain.NewsUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NewsUserMapper {

    @Insert(value = "insert into t_news_user(user_id,news_id) values(#{userId},#{newsId})")
    @Options(useGeneratedKeys = true)
    void addNewsUser(NewsUser newsUser);

    @Delete(value = "delete from t_news_user where id = #{id}")
    void deleteNewsUser(@Param(value = "id") Long id);
}
