package com.aj.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * druid数据库连接池配置类
 *
 * @author zhonghuashishan
 */
@Configuration
@MapperScan(basePackages = "com.aj.mapper.news",
        sqlSessionFactoryRef = "newsSqlSessionFactory")
public class NewsDataSourceConfig {

    @Value("${news.datasource.url}")
    private String dbUrl;
    @Value("${news.datasource.username}")
    private String username;
    @Value("${news.datasource.password}")
    private String password;
    @Value("${news.datasource.driverClassName}")
    private String driverClassName;
    @Value("${news.datasource.initialSize}")
    private int initialSize;
    @Value("${news.datasource.minIdle}")
    private int minIdle;
    @Value("${news.datasource.maxActive}")
    private int maxActive;
    @Value("${news.datasource.maxWait}")
    private int maxWait;
    @Value("${news.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${news.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${news.datasource.validationQuery}")
    private String validationQuery;
    @Value("${news.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${news.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${news.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${news.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${news.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${news.datasource.filters}")
    private String filters;
    @Value("${news.datasource.connectionProperties}")
    private String connectionProperties;

    /**
     * 创建druid数据库连接池bean
     *
     * @return
     */
    @Bean(name = "newsDataSource")
    public DataSource newsDataSource() {
        DruidXADataSource datasource = new DruidXADataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        datasource.setConnectionProperties(connectionProperties);

        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setXaDataSource(datasource);

        return atomikosDataSource;
    }

    @Bean(name = "newsSqlSessionFactory")
    public SqlSessionFactory newsSqlSessionFactory(
            @Qualifier("newsDataSource") DataSource newsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(newsDataSource);
        return sessionFactory.getObject();
    }

}