package com.zhss.seckill.operation.domain;

/**
 * 秒杀商品
 */
public class SeckillProduct {

    /**
     * 秒杀商品id
     */
    private Long id;
    /**
     * 秒杀场次id
     */
    private Long sessionId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 秒杀价格
     */
    private Double seckillPrice;
    /**
     * 秒杀库存数量
     */
    private Long seckillStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Long getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(Long seckillStock) {
        this.seckillStock = seckillStock;
    }

}
