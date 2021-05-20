/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode692
 *
 * @author An Jun
 * @date 2021-05-20
 */
public class Leetcode692 {

    public static void main(String[] args) {
        Leetcode692 leetcode692 = new Leetcode692();
        String[] words = new String[10];

        words[0] = "0";
        words[1] = "1";
        words[2] = "2";
        words[3] = "2";
        words[4] = "3";
        words[5] = "3";
        words[6] = "3";
        words[7] = "4";
        words[8] = "4";
        words[9] = "4";
        words[9] = "4";
        words[6] = "5";
        words[7] = "5";
        words[8] = "5";
        words[9] = "5";
        words[9] = "5";

        leetcode692.topKFrequent(words, 4);
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>(k);
        TriePlus trie = new TriePlus();
        //先存入trie树中
        for (String word : words) {
            trie.insertCheckExists(word);
        }

        return result;
    }

}
