/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.algorithm;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 5. 最长回文子串
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 *
 * @author An Jun
 * @date 2021-07-06
 */
public class Leetcode5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("12321"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] ss = s.toCharArray();
        //先将每个字符插入一个#号
        //创建一个chars数组,保存每个字符前后插入一个#的新数组
        char[] chars = new char[s.length() * 2 + 1];
        int ii = 0;
        for (int i = 0; i < s.length() * 2 + 1; i++) {
            if (i % 2 == 0) {
                chars[i] = '#';
            } else {
                chars[i] = ss[ii++];
            }
        }
        //再创建一个s.length * 2 + 1长度的p数组
        //再计算p[0] -> p[s.length * 2]的值是多少
        int maxIndex = 0;
        int maxSize = 0;
        for (int i = 0; i < chars.length; i++) {
            int size = returnCheckCharRound(i, chars);
            if (size > maxSize) {
                maxSize = size;
                maxIndex = i;
            }
        }
        if (maxSize <= 1) {
            return s.substring(0, 1);
        }
        //p[0]的值就是当前0位置的字符处的最长回文字符长度,同时用两个临时变量记录最长回文字符的长度和最长回文字符中心的位置(用于最终获取回文串)
        String tmpResult = new String(chars).substring(maxIndex - maxSize, maxIndex + maxSize);
        return tmpResult.replaceAll("#", "");
    }

    private static int returnCheckCharRound(int i, char[] chars) {
        int result = 0;
        int tmp = 1;
        int prev;
        int next;
        while ((prev = i - tmp) >= 0 && (next = i + tmp) < chars.length) {
            if (chars[prev] != chars[next]) {
                break;
            }
            tmp++;
            result++;
        }
        return result;
    }

}
