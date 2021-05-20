/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * 208. 实现 Trie (前缀树)
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class TrieTree {

    public static TrieTree getInstance = new TrieTree();

    private final Dictory dictory;

    /**
     * Initialize your data structure here.
     */
    private TrieTree() {
        dictory = new Dictory();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        char firstLetter = chars[0];
        Node node = dictory.root.get(firstLetter);
        Node topNode = node;
        if (node == null) {
            //说明还没有这个首字母开头的数据
            Node firstNode = new Node(firstLetter);
            dictory.root.put(firstLetter, firstNode);
            topNode = firstNode;
            if (length == 1) {
                topNode.isWord = true;
                topNode.length = length;
            }
        }
        for (int i = 1; i < length; i++) {
            Node haveSameValueNode = topNode.getPossibleLetterNode(chars[i]);
            if (haveSameValueNode == null) {
                topNode = topNode.insert(chars[i]);
            } else {
                //说明已经存在,直接跳过
                topNode = haveSameValueNode;
            }
            if (i == length - 1) {
                topNode.isWord = true;
                topNode.length = length;
            }
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        char firstLetter = chars[0];
        Node node = dictory.root.get(firstLetter);
        boolean flag = false;
        if (node != null) {
            if (node.isWord && length == 1) {
                flag = true;
            }
            for (int i = 1; i < length; i++) {
                if (node == null) {
                    break;
                }
                node = node.getPossibleLetterNode(chars[i]);
                if (node == null || !node.isWord || node.length != length) {
                    continue;
                }
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || "".equals(prefix)) {
            return false;
        }
        char[] chars = prefix.toCharArray();
        int length = chars.length;
        char firstLetter = chars[0];
        Node node = dictory.root.get(firstLetter);
        boolean flag = false;
        if (node != null) {
            if (length == 1) {
                flag = true;
            }
            for (int i = 1; i < length; i++) {
                node = node.getPossibleLetterNode(chars[i]);
                if (node == null) {
                    break;
                }
                if (i == length - 1) {
                    flag = true;
                }
            }
        }
        return flag;
    }


    static class Dictory {

        Map<Character, Node> root = new HashMap<>();

    }

    static class Node {

        int length;

        Character value;

        HashMap<Character, Node> nextMap;

        boolean isWord;

        Node(Character value) {
            this.value = value;
        }

        Node getPossibleLetterNode(Character value) {
            if (nextMap != null && nextMap.size() > 0) {
                return nextMap.get(value);
            }
            return null;
        }

        Node insert(Character value) {
            if (nextMap == null || nextMap.size() <= 0) {
                nextMap = new HashMap<>(2, 0.95f);
            }
            Node node = new Node(value);
            nextMap.put(value, node);
            return node;
        }

    }

}
