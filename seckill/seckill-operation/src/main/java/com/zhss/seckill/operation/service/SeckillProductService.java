package com.zhss.seckill.operation.service;

import com.zhss.seckill.operation.domain.SeckillProduct;

/**
 * 秒杀商品Service接口
 */
public interface SeckillProductService {

    /**
     * 增加秒杀商品
     * @param seckillProduct 秒杀商品
     */
    void add(SeckillProduct seckillProduct);

}
