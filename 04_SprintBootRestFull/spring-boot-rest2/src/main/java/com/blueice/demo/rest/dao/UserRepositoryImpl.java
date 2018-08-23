package com.blueice.demo.rest.dao;

import com.blueice.demo.rest.bean.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2018/8/22.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    //用于生成递增id
    private static AtomicLong counter = new AtomicLong();
    //存储数据的Map
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<Long, User>();

    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if(id == null || id <=0){
            id = counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id, user);
        return user;
    }

    @Override
    public void delteUser(Long id) {
        this.userMap.remove(id);
    }

    @Override
    public User getUserById(Long id) {
        return this.userMap.get(id);
    }

    @Override
    public List<User> listUser() {
        return new ArrayList<User>(this.userMap.values());
    }
}
