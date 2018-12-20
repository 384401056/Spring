package com.blueice.springboot.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //这可以注入dao或者mapper
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        logger.info("登录用户名：" + name);

        //返回的这个User是security中实现了UserDetails接口的类。
        //第一个参数是：传入的用户名，第二个是数据库中查询到的密码，这里用字符代替
        //第三个参数是：权限集合,这里用一个工具方法将字符串转为 GrantedAuthority 集合
        logger.info("密码：" + passwordEncoder.encode("123456").toString());
        return new User(name, passwordEncoder.encode("123456"),
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user"));//当使用hasRole()判断权限时，这里的ROLE_必须是大写的.
                AuthorityUtils.commaSeparatedStringToAuthorityList("user"));//当使用hasAnyAuthority()判断权限时，字符就必须一致.

    }


}
