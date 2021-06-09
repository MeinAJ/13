/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.rate.limiter;

/**
 * RateLimiter
 *
 * @author An Jun
 * @date 2021-06-09
 */
public class Main {

    private static RateLimiter rateLimiter = new RateLimiter(2);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(rateLimiter.tryAcquire());
                }
            }.start();
        }
    }

}
