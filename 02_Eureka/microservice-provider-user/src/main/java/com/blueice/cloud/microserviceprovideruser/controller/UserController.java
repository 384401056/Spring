package com.blueice.cloud.microserviceprovideruser.controller;

import com.blueice.cloud.microserviceprovideruser.beans.User;
import com.blueice.cloud.microserviceprovideruser.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        try {
            return userDao.findById(id).get();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/userlist")
    public List<User> getUserList(){
        try {
            return userDao.findAll();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
