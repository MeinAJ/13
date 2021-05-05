package com.aj.mapper.news;

import com.aj.domain.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {
    void addNews(News news);
}
