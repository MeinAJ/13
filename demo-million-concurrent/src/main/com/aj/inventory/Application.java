package com.aj.inventory;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@EnableAutoConfiguration
@SpringBootApplication
@MapperScan(basePackages = "com.aj.inventory.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mappers/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JedisCluster JedisClusterFactory() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("192.168.0.103", 7001));
        jedisClusterNodes.add(new HostAndPort("192.168.0.103", 7002));
        jedisClusterNodes.add(new HostAndPort("192.168.0.104", 7003));
        jedisClusterNodes.add(new HostAndPort("192.168.0.104", 7004));
        jedisClusterNodes.add(new HostAndPort("192.168.0.105", 7005));
        jedisClusterNodes.add(new HostAndPort("192.168.0.105", 7006));
        return new JedisCluster(jedisClusterNodes, 2000, 2000, 2, "redis-pass", new GenericObjectPoolConfig());
    }

}
