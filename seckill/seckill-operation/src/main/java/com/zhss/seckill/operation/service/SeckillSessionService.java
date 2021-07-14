package com.zhss.seckill.operation.service;

import com.zhss.seckill.operation.domain.SeckillSession;

/**
 * 秒杀场次Service接口
 */
public interface SeckillSessionService {

    /**
     * 增加秒杀场次
     * @param seckillSession 秒杀场次
     */
    void add(SeckillSession seckillSession);

}
