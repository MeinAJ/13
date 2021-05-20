/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.filter.service;

import com.aj.sensitive.filter.domain.Sensitive;
import com.aj.sensitive.filter.mapper.SensitiveMapper;
import com.aj.sensitive.filter.trie.TrieTree;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author An Jun
 * @date 2021-04-21
 */
@Service(value = "testServiceImpl")
public class SensitiveServiceImpl implements SensitiveService {

    @Autowired
    private SensitiveMapper sensitiveMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<Sensitive> listAll() {
        return sensitiveMapper.listAll();
    }

    @Override
    public String checkComment(String comment) {
        System.out.println("comment=" + comment);
        Set<String> wordSet = getWordSet(comment);
        boolean hasSensitiveWord = false;
        if (!CollectionUtils.isEmpty(wordSet)) {
            try {
                RBloomFilter<Object> filter = redissonClient.getBloomFilter("sensitive-word-bloom-filter-test");
                for (String word : wordSet) {
                    System.out.println("word=" + word);
                    if (hasSensitiveWord = filter.contains(word)) {
                        break;
                    }
                }
            } catch (Exception e) {
                TrieTree dic = TrieTree.getInstance;
                for (String word : wordSet) {
                    hasSensitiveWord = dic.search(word);
                    if (hasSensitiveWord) {
                        break;
                    }
                }
            }
        }
        return hasSensitiveWord ? "fail" : "success";
    }

    private String getWordString(Set<String> wordSet) {
        StringBuilder stringBuffer = new StringBuilder();
        for (String word : wordSet) {
            stringBuffer.append("'").append(word).append("'").append(",");
        }
        return stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
    }

    private Set<String> getWordSet(String comment) {
        TokenStream ts;
        StringReader reader;
        Set<String> wordSet = new HashSet<>();
        try {
            //创建分词对象
            Analyzer anal = new IKAnalyzer(true);
            reader = new StringReader(comment);
            //分词
            ts = anal.tokenStream("", reader);
            ts.reset();
            CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
            //遍历分词数据
            while (ts.incrementToken()) {
                wordSet.add(term.toString());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordSet;
    }

}
