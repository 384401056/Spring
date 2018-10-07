package com.blueice.cloud.microservicesimpleconsumermovie.controller;

import com.blueice.cloud.microservicesimpleconsumermovie.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/8/16.
 */

@RestController
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/movie/{id}")
    public User getUserById(@PathVariable Long id) {
        //调用一个微服务.
        return restTemplate.getForObject("http://localhost:8082/user/" + id, User.class);
    }
}
