package com.aj.sensitive.filter.listener;

import com.aj.sensitive.filter.bloomfilter.BloomFilterInit;
import com.aj.sensitive.filter.context.SpringContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component("initListener")
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取ApplicationContext
        ServletContext sc = sce.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
        SpringContext.setSpringContext(webApplicationContext);
        //初始化布隆过滤器
        BloomFilterInit.init(webApplicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //do nothing
    }
}
