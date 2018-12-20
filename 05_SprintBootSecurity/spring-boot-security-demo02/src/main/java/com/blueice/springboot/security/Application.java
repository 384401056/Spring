package com.blueice.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class
//}) //停用Spring Security
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

