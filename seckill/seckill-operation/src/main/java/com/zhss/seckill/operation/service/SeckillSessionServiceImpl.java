package com.zhss.seckill.operation.service;

import com.zhss.seckill.operation.dao.SeckillSessionDAO;
import com.zhss.seckill.operation.domain.SeckillSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀场次Service组件
 */
@Service
public class SeckillSessionServiceImpl implements SeckillSessionService {

    /**
     * 秒杀场次DAO组件
     */
    @Autowired
    private SeckillSessionDAO seckillSessionDAO;

    /**
     * 增加秒杀场次
     * @param seckillSession 秒杀场次
     */
    public void add(SeckillSession seckillSession) {
        seckillSessionDAO.add(seckillSession);
    }

}
