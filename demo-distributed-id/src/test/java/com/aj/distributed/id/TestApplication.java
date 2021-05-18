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

/**
 * Test
 *
 * @author An Jun
 * @date 2021-05-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private CachedGenerator cachedGenerator;

    @Test
    public void test1() {
        for (int i = 0; i < 8; i++) {
            nextId();
        }
    }

    public void nextId() {
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(cachedGenerator.nextId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
