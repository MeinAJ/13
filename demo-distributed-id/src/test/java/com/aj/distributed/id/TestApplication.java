/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.distributed.id;

import com.aj.distributed.id.snowflake.plus.CachedGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test
 *
 * @author An Jun
 * @date 2021-05-18
 */
@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private CachedGenerator cachedGenerator;

    private final AtomicInteger count = new AtomicInteger(0);

    private final ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();

    @Test
    public void test1() {
        for (int i = 0; i < 1025; i++) {
            nextId();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ddd");
    }

    public void nextId() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Long id = cachedGenerator.nextId();
                    if (id != null) {
                        count.incrementAndGet();
                        map.put(id, id);
                    } else{
                        System.out.println("id不存在");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
