package com.aj;

import java.util.HashMap;
import java.util.Map;

public class ObjectMain {

    public static void main(String[] args) throws InterruptedException {

//        TrieTree dic = TrieTree.getInstance;
//        dic.insert("1");
//        dic.insert("12");
//        dic.insert("123");
//        dic.insert("1234");
//        dic.insert("12345");
//        dic.insert("123456");
//
//        dic.insert("2");
//        dic.insert("22");
//        dic.insert("223");
//        dic.insert("2234");
//        dic.insert("22345");
//        dic.insert("223456");
//
//        while (true){
//            Thread.sleep(5000);
//        }
        //查看jvm空闲内存大小，单位字节
        long start = Runtime.getRuntime().freeMemory();

        Map<String,String> map = new HashMap<>();
        for(int i = 0 ;i < 100000; i ++){
            map.put("11111111111111111111111111111111111111"+i,"你叫什么名字");
        }
        //查看jvm空闲内存大小

        long end = Runtime.getRuntime().freeMemory();
        //获取map使用的内存大小，并将其转为兆单位
        double useMemory = (start - end);
        //打印map使用的内存大小
        System.out.println("使用掉的内存："+ useMemory + "byte");
        //打印总内存大小
        System.out.println("内存总量："+Runtime.getRuntime().totalMemory() + "kb");
        //保持map的引用
//        for(Map.Entry<String, String> map1 : map.entrySet()){
//            System.out.println(map1.getKey() + " :" + map1.getValue());
//        }
        while (true){
            Thread.sleep(5000);
        }
    }

}
