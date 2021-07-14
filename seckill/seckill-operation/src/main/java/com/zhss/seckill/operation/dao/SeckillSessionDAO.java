package com.zhss.seckill.operation.dao;

import com.zhss.seckill.operation.domain.SeckillSession;

/**
 * 秒杀场次DAO接口
 */
public interface SeckillSessionDAO {

    /**
     * 增加秒杀场次
     * @param seckillSession 秒杀场次
     */
    void add(SeckillSession seckillSession);

}
