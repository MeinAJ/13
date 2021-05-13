/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.lru;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CustomLRUCacheImpl
 *
 * @author An Jun
 * @date 2021-05-12
 */
@SuppressWarnings("ALL")
public class CustomLRUCacheImpl<E> implements LRUCache<E> {

    public static void main(String[] args) {
        CustomLRUCacheImpl<String> cache = new CustomLRUCacheImpl<>();
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");

        cache.get("4");
        System.out.println("ddd");
    }

    int capacity = 4;

    Node<E> head;

    Node<E> tail;

    int count;

    HashMap<String, Node<E>> cache = new HashMap<>();

    ReentrantLock lock = new ReentrantLock();

    CustomLRUCacheImpl() {

    }

    CustomLRUCacheImpl(int capacity) {
        if (capacity <= 0) {
            return;
        }
        this.capacity = capacity;
    }

    @Override
    public E get(String key) {
        E value = null;
        try {
            lock.lock();
            boolean containsKey = cache.containsKey(key);
            if (!containsKey) {
                //没有值
                return value;
            }
            //返回值
            Node<E> eNode = cache.get(key);
            value = eNode.node.value;

            //调整顺序,如果就在head,不调整
            if (!(this.head == eNode)) {
                Node<E> preHead = this.head;
                Node<E> tmpPre = eNode.pre;
                Node<E> tmpNext = eNode.next;
                Node<E> preTail = this.tail;
                if (this.tail == eNode) {
                    //是最后一个数据,那就把tail放到head
                    this.tail = preTail.pre;
                    this.head = preTail;
                    preHead.pre = preTail;
                    preTail.next = preHead;
                    preTail.pre.next = null;
                    preTail.pre = null;
                } else {
                    //不是最后一个数据
                    tmpPre.next = eNode.next;
                    tmpNext.pre = eNode.pre;
                    eNode.pre = null;
                    eNode.next = null;
                    this.head = eNode;
                    eNode.next = preHead;
                    preHead.pre = eNode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return value;
    }

    @Override
    public void put(String key, E value) {
        try {
            lock.lock();
            boolean containsKey = cache.containsKey(key);
            if (containsKey) {
                //存在,直接覆盖
                Node<E> eNode = cache.get(key);
                eNode.node.value = value;
            } else {
                //不存在,判断数量是否超过阈值
                Node<E> node = new Node<>(new Entry<>(key, value), null, this.head);
                if (count + 1 <= capacity) {
                    //不超过
                    if (this.head == null) {
                        this.head = node;
                        this.head.pre = null;
                        this.head.next = node;
                        this.tail = node;
                        this.tail.pre = node;
                        this.tail.next = null;
                    } else {
                        Node<E> preHead = this.head;
                        this.head = node;
                        preHead.pre = node;
                        node.next = preHead;
                    }
                    cache.put(key, node);
                    count++;
                } else {
                    //超过,删除最先插入的数据,也就是队尾的数据,再插入数据
                    Node<E> tmpTail = this.tail;
                    this.tail = tail.pre;
                    this.tail.next = null;
                    Node<E> removeValue = cache.remove(tmpTail.node.key);
                    System.out.println("内存空间不足,移除缓存,key=" + tmpTail.node.key + ",value=" + removeValue.node.value);

                    tmpTail = null;

                    Node<E> preHead = this.head;
                    this.head = node;
                    preHead.pre = node;
                    node.next = preHead;

                    cache.put(key, node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    static class Node<E> {

        Node<E> pre;

        Node<E> next;

        Entry<E> node;

        Node(Entry<E> node, Node<E> pre, Node<E> next) {
            this.node = node;
            this.pre = pre;
            this.next = next;
        }


    }

    static class Entry<E> {

        String key;

        E value;

        Entry(String key, E value) {
            this.key = key;
            this.value = value;
        }

    }

}
