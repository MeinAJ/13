/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.service;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * TokenServiceImpl
 *
 * @author An Jun
 * @date 2021-04-30
 */
@Service(value = "tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean tokenIsOk(String token) {
        try {
            if (StringUtils.hasLength(token)) {
                RBucket<String> bucket = redissonClient.getBucket(token);
                return bucket.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
