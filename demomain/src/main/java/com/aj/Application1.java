/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Application
 *
 * @author An Jun
 * @date 2021-04-16
 */
public class Application1 {

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            try {
                System.out.println(22);
                flag = false;
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("11");
            }
        }

//
//        for (int i = 0; i < 1000; i++) {
//            new Thread(){
//                @Override
//                public void run() {
//                    get();
//                }
//            }.start();
//        }
    }

    private static void get() {
        String s = doGet("http://192.168.0.103/getNewsInfo?path=getNewsInfo&newsId=3");
        System.out.println(s);
    }

    public static String doGet(String httpUrl) {
        //链接
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setReadTimeout(15000);
            //开始连接
            connection.connect();
            //获取响应数据
            if (connection.getResponseCode() == 200) {
                //获取返回的数据
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭远程连接
            assert connection != null;
            connection.disconnect();
        }
        return result.toString();
    }
}
