package com.blueice.demo.demo01_gradle.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @GetMapping("/hello")
    public String Hello(){
        return "Hello World! Welcome to visit way";
    }
}
