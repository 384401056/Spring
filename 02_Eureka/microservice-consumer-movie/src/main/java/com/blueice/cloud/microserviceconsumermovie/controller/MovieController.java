package com.blueice.cloud.microserviceconsumermovie.controller;

import com.blueice.cloud.microserviceconsumermovie.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

@RestController
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User getUserById(@PathVariable Long id) {

        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        System.out.println("111:" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        //通过Applicatname调用另一个微服务.
        return restTemplate.getForObject("http://microservice-provider-user/user/" + id, User.class);
    }

    @GetMapping("/movie/list")
    public List<User> getUserList() {

        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        System.out.println("111:" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        return restTemplate.exchange("http://microservice-provider-user/userlist",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();
    }
}
