/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.aj.unique.dao;

import org.apache.ibatis.annotations.Param;

public interface UniqueRecordDao {

    void insert(@Param(value = "uniqueValue") String uniqueValue);

}
