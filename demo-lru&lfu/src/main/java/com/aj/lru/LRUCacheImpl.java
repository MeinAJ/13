/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LRUCache
 *
 * @author An Jun
 * @date 2021-05-12
 */
@SuppressWarnings("ALL")
public class LRUCacheImpl<E> implements LRUCache<E> {

    private final Integer CAPACITY = 10;

    private Integer max = CAPACITY;

    private Integer count = 0;

    private final LinkedHashMap<String, E> cache = new LinkedHashMap<String, E>();

    private final ReentrantLock lock = new ReentrantLock();

    public LRUCacheImpl() {
    }

    public LRUCacheImpl(Integer max) throws Exception {
        checkConfigSize(max);
        this.max = max;
    }

    private void checkConfigSize(Integer max) throws Exception {
        if (max == null || max <= 0) {
            throw new Exception("数量为负数");
        }
    }

    @Override
    public E get(String key) {
        boolean containsKey = cache.containsKey(key);
        E result = null;
        if (!containsKey) {
            System.out.println("缓存不存在,key=" + key);
            return null;
        }
        //移动这个缓存至首位
        try {
            this.lock.lock();
            //先删除这个缓存
            result = cache.remove(key);
            System.out.println("get方法,先移除缓存,key=" + key);
            //再插入到首位
            cache.put(key, result);
            System.out.println("get方法,再重新缓存,key=" + key + ",value=" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
        return result;
    }

    @Override
    public void put(String key, E value) {
        try {
            lock.lock();
            boolean containsKey = cache.containsKey(key);
            if (containsKey) {
                //存在,直接覆盖
                System.out.println("存在,直接覆盖,key=" + key + ",value=" + value);
                cache.replace(key, value);
                return;
            }

            //缓存不存在时
            boolean sizeIsOk = whenAddCheckCapacity();
            if (!sizeIsOk) {
                //超过规定个数,移除最后一个
                String removeKey = null;
                Iterator<Map.Entry<String, E>> iterator = cache.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, E> next = iterator.next();
                    removeKey = next.getKey();
                    break;
                }
                E removeValue = cache.remove(removeKey);
                System.out.println("移除了一个缓存,key=" + removeKey + ",value=" + removeValue);
            }
            cache.put(key, value);
            count++;
            System.out.println("缓存一个数据,key=" + key + ",value=" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private boolean whenAddCheckCapacity() {
        return count + 1 <= this.max;
    }

}
