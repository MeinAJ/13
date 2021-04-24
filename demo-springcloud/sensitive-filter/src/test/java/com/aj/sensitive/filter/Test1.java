package com.aj.sensitive.filter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Test
    public void test() throws IOException {
        TokenStream ts = null;
        StringReader reader = null;
        try {
            String text = "基于java语言开发的轻量级的中文分词工具包";
            //创建分词对象
            Analyzer anal = new IKAnalyzer(true);
            reader = new StringReader(text);
            //分词
            ts = anal.tokenStream("", reader);
            ts.reset();
            CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
            //遍历分词数据
            while (ts.incrementToken()) {
                System.out.print(term.toString() + "|");
            }
            reader.close();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
