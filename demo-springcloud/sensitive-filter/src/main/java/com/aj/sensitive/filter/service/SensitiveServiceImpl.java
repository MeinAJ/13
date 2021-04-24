/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.filter.service;

import com.aj.sensitive.filter.domain.Sensitive;
import com.aj.sensitive.filter.mapper.SensitiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author An Jun
 * @date 2021-04-21
 */
@Service(value = "testServiceImpl")
public class SensitiveServiceImpl implements SensitiveService {

    @Autowired
    private SensitiveMapper sensitiveMapper;

    @Override
    public List<Sensitive> listAll() {
        return sensitiveMapper.listAll();
    }
}
