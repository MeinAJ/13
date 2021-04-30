/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * IdempotenceApplication
 *
 * @author An Jun
 * @date 2021-04-30
 */
@SpringBootApplication
public class IdempotenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdempotenceApplication.class, args);
    }

}
