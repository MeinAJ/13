/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.service;

/**
 * TokenService
 *
 * @author An Jun
 * @date 2021-04-30
 */
public interface TokenService {

    boolean tokenIsOk(String token);

}
