/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.lru;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

class LRUCacheA {

    public static void main(String[] args) {
        LRUCacheA cache = new LRUCacheA(2);
        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    Integer capacity;

    Node head;

    Node tail;

    Integer count = 0;

    HashMap<Integer, Node> cache = new HashMap<>();

    ReentrantLock lock = new ReentrantLock();

    public LRUCacheA(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer get(Integer key) {
        Integer value = -1;
        try {
            lock.lock();
            boolean containsKey = cache.containsKey(key);
            if (!containsKey) {
                //没有值
                return value;
            }
            //返回值
            Node eNode = cache.get(key);
            value = eNode.node.value;

            //调整顺序,如果就在head,不调整
            change(eNode);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    public void put(Integer key, Integer value) {
        try {
            lock.lock();
            boolean containsKey = cache.containsKey(key);
            if (containsKey) {
                //存在,直接覆盖
                Node eNode = cache.get(key);
                eNode.node.value = value;
                change(eNode);
            } else {
                //不存在,判断数量是否超过阈值
                Node node = new Node(new Entry(key, value), null, this.head);
                if (count + 1 <= capacity) {
                    //不超过
                    if (this.head == null) {
                        this.head = node;
                        this.tail = node;
                        this.head.next = this.tail;
                        this.tail.pre = this.head;
                    } else {
                        this.tail.next = null;
                        Node preHead = this.head;
                        this.head = node;
                        preHead.pre = node;
                        node.next = preHead;
                    }
                    cache.put(key, node);
                    count++;
                } else {
                    //超过,删除最先插入的数据,也就是队尾的数据,再插入数据
                    Node tmpTail = this.tail;
                    this.tail = tail.pre;
                    this.tail.next = null;
                    Node removeValue = cache.remove(tmpTail.node.key);
                    System.out.println("内存空间不足,移除缓存,key=" + tmpTail.node.key + ",value=" + removeValue.node.value);

                    tmpTail = null;

                    Node preHead = this.head;
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


    static class Node {

        Node pre;

        Node next;

        Entry node;

        Node(Entry node, Node pre, Node next) {
            this.node = node;
            this.pre = pre;
            this.next = next;
        }


    }

    static class Entry {

        Integer key;

        Integer value;

        Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

    }

    private void change(Node eNode) {
        if (!(this.head == eNode)) {
            Node preHead = this.head;
            Node tmpPre = eNode.pre;
            Node tmpNext = eNode.next;
            Node preTail = this.tail;
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
    }

}
