package com.zhss.seckill.operation.dao;

import com.zhss.seckill.operation.domain.SeckillProduct;

/**
 * 秒杀商品DAO接口
 */
public interface SeckillProductDAO {

    /**
     * 增加秒杀商品
     * @param seckillProduct 秒杀商品
     */
    void add(SeckillProduct seckillProduct);

}
