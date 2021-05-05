package com.aj;

import com.aj.config.UserDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ServletComponentScan
@Import(UserDataSourceConfig.class)
public class AtomikosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomikosApplication.class, args);
    }
}
