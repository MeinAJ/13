/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.idempotence;

import com.aj.idempotence.config.DruidDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * IdempotenceApplication
 *
 * @author An Jun
 * @date 2021-04-30
 */
@SpringBootApplication
@MapperScan("com.aj.idempotence.dao")
@EnableAspectJAutoProxy
@Import(DruidDataSourceConfig.class)
public class IdempotenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdempotenceApplication.class, args);
    }

}
