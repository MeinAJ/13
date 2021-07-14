package com.zhss.seckill.operation.dao;

import com.zhss.seckill.operation.domain.SeckillProduct;
import com.zhss.seckill.operation.mapper.SeckillProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 秒杀商品DAO组件
 */
@Repository
public class SeckillProductDAOImpl implements SeckillProductDAO {

    /**
     * 秒杀商品Mapper组件
     */
    @Autowired
    private SeckillProductMapper seckillProductMapper;

    /**
     * 增加秒杀商品
     * @param seckillProduct 秒杀商品
     */
    public void add(SeckillProduct seckillProduct) {
        seckillProductMapper.insert(seckillProduct);
    }

}
