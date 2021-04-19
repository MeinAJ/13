package com.aj.inventory;

import com.aj.inventory.model.News;
import com.aj.inventory.service.NewsService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test
 *
 * @author An Jun
 * @date 2021-04-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    @Autowired
    private NewsService newsService;

    @Test
    public void getNewsInfo() {
        News newsInfo = newsService.getNewsInfo(7L);
        System.out.println("result=" + JSON.toJSONString(newsInfo));
        newsInfo = newsService.getNewsInfo(7L);
        System.out.println("result=" + JSON.toJSONString(newsInfo));
        newsInfo = newsService.getNewsInfo(7L);
        System.out.println("result=" + JSON.toJSONString(newsInfo));
    }

}