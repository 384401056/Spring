package com.blueice.cloud.microservicesimpleprovideruser.controller;

import com.blueice.cloud.microservicesimpleprovideruser.beans.User;
import com.blueice.cloud.microservicesimpleprovideruser.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Administrator on 2018/8/16.
 */

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userDao.findById(id).get();
    }
}
