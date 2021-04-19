package com.aj.inventory;

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
    private com.aj.service.TestService testService;

    @Test
    public void test() {
        String typeId = "type1111111111";
        // 模拟第一次保存
        String returnStr1 = testService.update(typeId);
        System.out.println(returnStr1);
        // 模拟第二次保存
        String returnStr2 = testService.select(typeId);
        System.out.println(returnStr2);
    }

    @Test
    public void testSave() {
        String typeId = "type111";
        // 模拟第一次保存
        String returnStr1 = testService.save(typeId);
        System.out.println(returnStr1);
        // 模拟第二次保存
        String returnStr2 = testService.save(typeId);
        System.out.println(returnStr2);
    }

    @Test
    public void testUpdate() {
        String typeId = "type111";
        // 模拟第一次查询
        String returnStr1 = testService.select(typeId);
        System.out.println(returnStr1);
        // 模拟第二次查询
        String returnStr2 = testService.select(typeId);
        System.out.println(returnStr2);
        // 模拟更新
        String returnStr3 = testService.update(typeId);
        System.out.println(returnStr3);
        // 模拟查询
        String returnStr4 = testService.select(typeId);
        System.out.println(returnStr4);
    }

    @Test
    public void testDelete() {
        String typeId = "type111";
        // 模拟第一次查询
        String returnStr1 = testService.select(typeId);
        System.out.println(returnStr1);
        // 模拟第二次查询
        String returnStr2 = testService.select(typeId);
        System.out.println(returnStr2);
        // 模拟删除
        String returnStr3 = testService.delete(typeId);
        System.out.println(returnStr3);
        // 模拟查询
        String returnStr4 = testService.select(typeId);
        System.out.println(returnStr4);
    }

    @Test
    public void testSelect() {
        String typeId = "type1111";
        // 模拟第一次查询
        String returnStr1 = testService.select(typeId);
        System.out.println(returnStr1);
        // 模拟第二次查询
        String returnStr2 = testService.select(typeId);
        System.out.println(returnStr2);
    }

}