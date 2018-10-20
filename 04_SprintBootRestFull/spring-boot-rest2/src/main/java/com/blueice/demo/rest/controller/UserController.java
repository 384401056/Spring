package com.blueice.demo.rest.controller;

import com.blueice.demo.rest.bean.User;
import com.blueice.demo.rest.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String getUser() {
        return "Hello Spring Security";
    }

    @GetMapping("list")
    public List<User> getUserList() {
        return userRepository.listUser();
    }

    @GetMapping("hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.getUserById(id);
    }

    @PostMapping("add")
    public User createUser(@RequestBody User user) {
        return userRepository.saveOrUpdateUser(user);
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User oldUser = this.getUser(id);
        if (oldUser != null) {
            user.setId(id);
            return userRepository.saveOrUpdateUser(user);
        } else {
            return null;
        }
    }

    @DeleteMapping("del/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.delteUser(id);
    }


}
