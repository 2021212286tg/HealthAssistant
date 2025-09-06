package com.example.healthassisstant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@EnableAsync // 开启异步支持
public class HealthAssisstantApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthAssisstantApplication.class, args);
    }

}
