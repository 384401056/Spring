package com.blueice.demo.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class SpringBootRest2Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRest2Application.class, args);
	}
}
