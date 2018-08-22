package com.blueice.demo.rest.dao;

import com.blueice.demo.rest.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22.
 */
public interface UserRepository {
    User saveOrUpdateUser(User user);

    void delteUser(Long id);

    User getUserById(Long id);

    List<User> listUser();
}
