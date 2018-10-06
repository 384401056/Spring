package com.blueice.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "/index.html";
    }

    @GetMapping("/hello")
    public String hello() {
        return "/hello.html";
    }

    @GetMapping("/login")
    public String login() {
        return "/login.html";
    }

}
