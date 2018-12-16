package com.blueice.springboot.security.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping
    public String getUser(){
        return "user";
    }

    @GetMapping("/list")
    public String getUserList(){
        return "user user user";
    }

    /**
     * 获取已经登录用户的 Authentication 信息
     * @param authentication
     * @return
     */
    @GetMapping("/info")
    public Object getAuthentication(Authentication authentication){
        return authentication;
    }
}
