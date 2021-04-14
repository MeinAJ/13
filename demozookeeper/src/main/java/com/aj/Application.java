/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Application
 *
 * @author An Jun
 * @date 2021-04-14
 */
public class Application {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "192.168.31.184:2181,192.168.31.207:2181,192.168.31.192:2181",
                retryPolicy);
        client.start();

        client.create()
                .creatingParentsIfNeeded()
                .forPath("/user/dir", "hello world".getBytes());

        System.out.println(new String(client.getData().forPath("/my/path")));
    }

}
