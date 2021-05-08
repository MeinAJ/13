package com.aj.news.mapper;

import com.aj.news.api.domain.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {

    @Insert(value = "insert into t_news(name) values(#{name})")
    void addNews(News news);

}
