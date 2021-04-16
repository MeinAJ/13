/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.service;

/**
 * TestService
 *
 * @author An Jun
 * @date 2021-04-16
 */
public interface TestService {

    String save(String typeId);

    String update(String typeId);

    String delete(String typeId);

    String select(String typeId);

}
