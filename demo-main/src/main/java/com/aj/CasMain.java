/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.concurrent.atomic.LongAdder;

/**
 * CasMain
 *
 * @author An Jun
 * @date 2021-07-05
 */
public class CasMain {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
    }

}
