/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.transactional.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * TestMapper
 *
 * @author An Jun
 * @date 2021-04-21
 */
public interface TestMapper {

    @Update(value = "update t_user set name = #{name}")
    void updateUser(@Param(value = "name") String name);

    @Update(value = "update t_news set title = #{title}")
    void updateNews(@Param(value = "title") String title);

}
