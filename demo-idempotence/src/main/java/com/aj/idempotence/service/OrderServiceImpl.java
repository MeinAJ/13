/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.service;

import com.aj.idempotence.annotations.Unique;
import com.aj.idempotence.annotations.UniqueValue;
import com.aj.idempotence.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 *
 * @author An Jun
 * @date 2021-04-30
 */
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Unique
    public void createOrder(@UniqueValue String token) {
        long goodsId = System.currentTimeMillis() / 1000;
        orderDao.insert(goodsId, goodsId / 1000);
    }

}
