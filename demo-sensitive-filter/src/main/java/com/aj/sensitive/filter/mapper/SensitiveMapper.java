/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.filter.mapper;

import com.aj.sensitive.filter.domain.Sensitive;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * TestMapper
 *
 * @author An Jun
 * @date 2021-04-21
 */
public interface SensitiveMapper {

    @Select(value = "select word from t_sensitive")
    List<Sensitive> listAll();

    @Select(value = "select id from t_sensitive where word in (${word}) limit 1")
    Long getOnePossibleWord(@Param(value = "word") String word);

}
