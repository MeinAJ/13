package com.zhss.seckill.operation.mapper;

import com.zhss.seckill.operation.domain.SeckillSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 秒杀场次Mapper组件
 */
@Mapper
public interface SeckillSessionMapper {

    /**
     * 插入秒杀场次
     * @param seckillSession
     */
    @Insert("INSERT INTO seckill_session(session_date,session_time) " +
            "VALUES(#{sessionDate},#{sessionTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(SeckillSession seckillSession);

}
