/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence.configurations;

import com.aj.idempotence.interceptor.TokenHandlerInterceptor;
import com.aj.idempotence.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * ApiConfig
 *
 * @author An Jun
 * @date 2021-04-30
 */
@Configuration
public class ApiConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenHandlerInterceptor(tokenService));
        super.addInterceptors(registry);
    }

}
