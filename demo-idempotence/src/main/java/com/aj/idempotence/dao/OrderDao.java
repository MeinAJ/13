/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.aj.idempotence.dao;

import org.apache.ibatis.annotations.Param;

public interface OrderDao {

    void insert(@Param(value = "goodsId") Long goodsId,
                @Param(value = "price") Long price);

}
