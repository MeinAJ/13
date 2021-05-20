/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.sensitive.init;

import com.aj.distributed.id.snowflake.plus.CachedGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * SnowWorkerInitListener
 *
 * @author An Jun
 * @date 2021-05-18
 */
@SuppressWarnings("ALL")
@Configuration
@Component
public class SnowWorkerInitConfig {

    @Bean
    public CachedGenerator cachedGenerator(@Qualifier(value = "workerInfo") TrieTree workerInfo) throws Exception {
        return new CachedGenerator(workerInfo);
    }

    @Bean
    public TrieTree workerInfo() throws Exception {
        return initWorkerInfo();
    }

    private TrieTree initWorkerInfo() throws Exception {
        TrieTree dic = new TrieTree();

        Integer workerId = getWorkerId();
        if (workerId == null) {
            throw new Exception("无法获取workerId");
        }
        dic.setWorkerId(workerId);

        return workerInfo;
    }

    private Set<String> getWorkerId() {
        Connection connection = null;
        PreparedStatement ps = null;
        Set<String> result = new HashSet<>();
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai",
                    "root",
                    "016312Aj.713");
            String sql = "select word from t_sensitive";
            //获取预处理statement
            ps = connection.prepareStatement(sql);
            //向数据库发出sql执行查询，查询出结果集
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("连接数据库异常");
            e.printStackTrace();
        } finally {
            //释放资源
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


}
