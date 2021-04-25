/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.transactional.service;

import com.aj.transactional.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * TestServiceImpl
 *
 * @author An Jun
 * @date 2021-04-21
 */
@Service(value = "testServiceImplV2")
public class TestServiceV2Impl implements TestServiceV2 {

    @Autowired
    private TestMapper testMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void test() {
        long l = System.currentTimeMillis();
        testMapper.updateUser(l + "");
        System.out.println("修改用户=" + l);
        testMapper.updateNews((l + 1) + "");
        System.out.println("修改新闻=" + (l + 1));
    }

}
