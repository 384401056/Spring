package com.blueice.cloud.microserviceprovideruser.dao;

import com.blueice.cloud.microserviceprovideruser.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/8/16.
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
}
