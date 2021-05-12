/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.lru;

/**
 * LRUCache
 *
 * @author An Jun
 * @date 2021-05-12
 */
public interface LRUCache<E> {

    E get(String key);

    void put(String key, E e);

}
