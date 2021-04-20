/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.inventory.context;

import org.springframework.context.ApplicationContext;

/**
 * SpringContext
 *
 * @author An Jun
 * @date 2021-04-20
 */
public class SpringContext {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setSpringContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }

}
