package com.aj.mapper.news;

import com.aj.domain.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {

    @Insert(value = "insert into t_news(name) values(#{name})")
    void addNews(News news);

}
