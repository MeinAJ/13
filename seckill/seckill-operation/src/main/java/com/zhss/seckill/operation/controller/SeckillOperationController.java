package com.zhss.seckill.operation.controller;

import com.zhss.seckill.operation.domain.SeckillProduct;
import com.zhss.seckill.operation.domain.SeckillSession;
import com.zhss.seckill.operation.service.SeckillProductService;
import com.zhss.seckill.operation.service.SeckillSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀运营服务的接口
 */
@RestController
@RequestMapping("/seckill/operation")
public class SeckillOperationController {

    // 为了便于我们测试
    // 我这里会把所有的HTTP接口简化为GET方式
    // POST/PUT，还得用postman
    // 传递参数，也直接在GET请求后附加，不用JSON传递参数了

    /**
     * 秒杀场次Service组件
     */
    @Autowired
    private SeckillSessionService seckillSessionService;

    /**
     * 秒杀商品Service组件
     */
    @Autowired
    private SeckillProductService seckillProductService;

    /**
     * 增加秒杀场次的接口
     * @return
     */
    @GetMapping("/session/add")
    public String addSeckillSession(SeckillSession seckillSession) {
        seckillSessionService.add(seckillSession);
        return "success";
    }

    /**
     * 增加秒杀场次下商品的接口
     */
    @GetMapping("/product/add")
    public String addSeckillProduct(SeckillProduct seckillProduct) {
        seckillProductService.add(seckillProduct);
        return "success";
    }

}
