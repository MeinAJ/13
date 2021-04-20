/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.inventory.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CuratorClientConfig
 *
 * @author An Jun
 * @date 2021-04-20
 */
@Configuration
public class CuratorClientConfig {

    @Bean(value = "curatorClient")
    public CuratorFramework getCuratorClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "192.168.0.207:2181,192.168.0.208:2181,192.168.0.209:2181",
                retryPolicy);
        client.start();
        return client;
    }


}
