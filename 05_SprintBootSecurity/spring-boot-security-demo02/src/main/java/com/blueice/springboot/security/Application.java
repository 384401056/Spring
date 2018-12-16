package com.blueice.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class
//}) //停用Spring Security
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

