/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Collections;

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
                "192.168.0.207:2181,192.168.0.208:2181,192.168.0.209:2181",
                retryPolicy);
        client.start();

        String path = "/my/path" + System.currentTimeMillis();
        client.create().creatingParentsIfNeeded().forPath(path, "hello world".getBytes());

        System.out.println(new String(client.getData().forPath(path)));


        //可重入锁
        InterProcessMutex lock = new InterProcessMutex(client, path);
        lock.acquire();
        lock.release();

        //Semaphore
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, path, 2);
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();

        //读写锁
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, path);
        InterProcessMutex readLock = readWriteLock.readLock();
        readLock.acquire();
        readLock.release();
        InterProcessMutex writeLock = readWriteLock.writeLock();
        writeLock.acquire();
        writeLock.release();

        //multilock

        InterProcessMultiLock multiLock = new InterProcessMultiLock(client, Collections.singletonList(path));
        multiLock.acquire();
        multiLock.release();
    }

}
