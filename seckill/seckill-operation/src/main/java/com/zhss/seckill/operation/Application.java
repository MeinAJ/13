package com.zhss.seckill.operation;

import com.zhss.seckill.operation.db.DruidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
@Import(DruidDataSourceConfig.class)
public class Application {
	
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
	
}
