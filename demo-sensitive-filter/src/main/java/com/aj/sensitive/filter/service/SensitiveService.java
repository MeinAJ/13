/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.filter.service;

import com.aj.sensitive.filter.domain.Sensitive;

import java.util.List;

/**
 * @author An Jun
 * @date 2021-04-21
 */
public interface SensitiveService {

    List<Sensitive> listAll();

    String checkComment(String comment);

}
