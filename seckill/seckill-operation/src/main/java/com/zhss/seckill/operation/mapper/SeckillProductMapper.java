package com.zhss.seckill.operation.mapper;

import com.zhss.seckill.operation.domain.SeckillProduct;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 秒杀商品Mapper组件
 */
@Mapper
public interface SeckillProductMapper {

    /**
     * 插入秒杀商品
     * @param seckillProduct 秒杀商品
     */
    @Insert("INSERT INTO seckill_product(session_id,product_id,seckill_price,seckill_stock) " +
            "VALUES(#{sessionId},#{productId},#{seckillPrice},#{seckillStock})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(SeckillProduct seckillProduct);

}
