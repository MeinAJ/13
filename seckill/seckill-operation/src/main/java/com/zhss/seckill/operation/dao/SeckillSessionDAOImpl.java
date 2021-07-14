package com.zhss.seckill.operation.dao;

import com.zhss.seckill.operation.domain.SeckillSession;
import com.zhss.seckill.operation.mapper.SeckillSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 秒杀场次DAO组件
 */
@Repository
public class SeckillSessionDAOImpl implements SeckillSessionDAO {

    /**
     * 秒杀场次Mapper组件
     */
    @Autowired
    private SeckillSessionMapper seckillSessionMapper;

    /**
     * 增加秒杀场次
     * @param seckillSession 秒杀场次
     */
    public void add(SeckillSession seckillSession) {
        seckillSessionMapper.insert(seckillSession);
    }

}
