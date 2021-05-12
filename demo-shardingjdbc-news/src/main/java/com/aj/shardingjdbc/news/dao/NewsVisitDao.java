package com.aj.shardingjdbc.news.dao;

import com.aj.shardingjdbc.news.domain.NewsVisit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NewsVisitDao {

    void addNewsVisit(NewsVisit newsVisit);

    List<NewsVisit> getNewsVisit(Long createTime);

    List<NewsVisit> page(@Param(value = "offset") Integer offset,
                         @Param(value = "limit") Integer limit);

}
