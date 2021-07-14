/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

/**
 * InnerClass
 *
 * @author An Jun
 * @date 2021-07-14
 */
public class InnerClass {

    public void call(String str) {
        System.out.println(str);
    }

    public class SubClass {

        public void print() {
            call("11");
        }

    }

}
