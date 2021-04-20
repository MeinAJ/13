/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.inventory.listener;

import com.aj.inventory.cache.KafkaConsumerThread;
import com.aj.inventory.cache.RebuildCacheProcessor;
import com.aj.inventory.context.SpringContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * InitListener
 * 项目启动时,初始化下面内容
 *
 * @author An Jun
 * @date 2021-04-20
 */
@SuppressWarnings("ALL")
@Component("initListener")
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取ApplicationContext
        ServletContext sc = sce.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
        SpringContext.setSpringContext(webApplicationContext);
        //开启kafka推送消息消费线程
        new Thread(new KafkaConsumerThread()).start();
        //开启重构缓存
        new Thread(new RebuildCacheProcessor()).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //do nothing
    }

}
