/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.transactional.service;

import com.aj.transactional.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TestServiceImpl
 *
 * @author An Junorg.springframework.transaction.aspectj.AnnotationTransactionAspect
 * @date 2021-04-21
 */
@Service(value = "testServiceImpl")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestServiceV2 testServiceV2;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        long l = System.currentTimeMillis();
        testMapper.updateUser(l + "");
        System.out.println("修改用户=" + l);
        testMapper.updateNews((l + 1) + "");
        System.out.println("修改新闻=" + (l + 1));
        testServiceV2.test();
    }

}
