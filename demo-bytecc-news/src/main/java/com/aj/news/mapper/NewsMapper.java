package com.aj.news.mapper;

import com.aj.news.api.domain.News;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NewsMapper {

    @Insert(value = "insert into t_news(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addNews(News news);

    @Delete(value = "delete from t_news where id = #{id}")
    void deleteNews(@Param(value = "id") Long id);
}
