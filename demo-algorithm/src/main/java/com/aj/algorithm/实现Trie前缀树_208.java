package com.aj.algorithm;

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
@SuppressWarnings("ALL")
public class 实现Trie前缀树_208 {

    public static void main(String[] args) {
        insert("123");
        insert("456");
        insert("789");

        System.out.println(search("12"));
        System.out.println(search("123"));
        System.out.println(search("1234"));
        System.out.println(search("12345"));

        System.out.println(search("45"));
        System.out.println(search("456"));
        System.out.println(search("4567"));
    }

    private static Dictory dictory = new Dictory();

    /**
     * Initialize your data structure here.
     */
    public 实现Trie前缀树_208() {

    }

    /**
     * Inserts a word into the trie.
     */
    public static void insert(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        char firstLetter = chars[0];
        Node node = dictory.root.get(firstLetter);
        if (node == null) {
            //说明还没有这个首字母开头的数据
            Node firstNode = new Node(firstLetter);
            dictory.root.put(firstLetter, firstNode);

            Node topNode = firstNode;
            for (int i = 1; i < length; i++) {
                topNode.left = new Node(chars[i]);
                topNode = topNode.left;
                if (i == length - 1) {
                    topNode.isWord = true;
                    topNode.length = length;
                }
            }

        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public static boolean search(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        char firstLetter = chars[0];
        Node node = dictory.root.get(firstLetter);
        boolean flag = false;
        if (node != null) {
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
    public static boolean startsWith(String prefix) {
        return true;
    }

    static class Dictory {
        Map<Character, Node> root = new HashMap<Character, Node>();
    }

    static class Node {
        int length;
        Character value;
        Node left;
        Node right;
        boolean isWord;

        Node() {

        }

        Node(Character value) {
            this.value = value;
        }

        Node(Node left, Node right, boolean isWord) {
            this.left = left;
            this.right = right;
            this.isWord = isWord;
        }

        boolean emptyNode() {
            return left == null && right == null;
        }

        boolean fullNode() {
            return left != null && right != null;
        }

        boolean haveLeftNode() {
            return left != null;
        }

        boolean haveRightNode() {
            return right != null;
        }

        Node getPossibleLetterNode(Character value) {
            if (haveLeftNode()) {
                if (left.value.equals(value)) {
                    return left;
                }
            }
            if (haveRightNode()) {
                if (right.value.equals(value)) {
                    return right;
                }
            }
            return null;
        }
    }
}
