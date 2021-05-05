package com.aj.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.sql.SQLException;

/**
 * druid数据库连接池配置类
 *
 * @author zhonghuashishan
 */
@Configuration
@MapperScan(basePackages = "com.aj.mapper.user",
        sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

    @Value("${user.datasource.url}")
    private String dbUrl;
    @Value("${user.datasource.username}")
    private String username;
    @Value("${user.datasource.password}")
    private String password;
    @Value("${user.datasource.driverClassName}")
    private String driverClassName;
    @Value("${user.datasource.initialSize}")
    private int initialSize;
    @Value("${user.datasource.minIdle}")
    private int minIdle;
    @Value("${user.datasource.maxActive}")
    private int maxActive;
    @Value("${user.datasource.maxWait}")
    private int maxWait;
    @Value("${user.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${user.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${user.datasource.validationQuery}")
    private String validationQuery;
    @Value("${user.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${user.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${user.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${user.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${user.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${user.datasource.filters}")
    private String filters;
    @Value("${user.datasource.connectionProperties}")
    private String connectionProperties;

    /**
     * 创建druid数据库连接池bean
     *
     * @return
     */
    @Bean(name = "newsDataSource")
    @Primary
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

    @Bean(name = "xatx")
    @Primary
    public JtaTransactionManager userTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean(name = "userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(
            @Qualifier("userDataSource") DataSource userDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(userDataSource);
        return sessionFactory.getObject();
    }

}