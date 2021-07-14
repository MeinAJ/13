package com.zhss.seckill.operation.service;

import com.zhss.seckill.operation.dao.SeckillProductDAO;
import com.zhss.seckill.operation.domain.SeckillProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀商品Service组件
 */
@Service
public class SeckillProductServiceImpl implements SeckillProductService {

    /**
     * 秒杀商品DAO组件
     */
    @Autowired
    private SeckillProductDAO seckillProductDAO;

    /**
     * 增加秒杀商品
     * @param seckillProduct 秒杀商品
     */
    public void add(SeckillProduct seckillProduct) {
        seckillProductDAO.add(seckillProduct);
    }

}
