package com.blueice.springboot.security.config;

import com.blueice.springboot.security.authentication.MyAuthenticationFailureHandler;
import com.blueice.springboot.security.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class BrowsSecurityConfig extends WebSecurityConfigurerAdapter {

    //用于给密码加密的对象
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 用来定义安全策略，
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin()
//               .loginPage("/login")//表示在没有授权前，任何访问需要授权才能访问的页面都会先跳转到/login登录页面
               //.defaultSuccessUrl("/welcome")//当一个用户成功登录后，即Spring Security认证成功后，我们的web应用将重定向到之前用户请求的页面，也可以客制化，使用defaultSuccessUrl方法将其重定向到指定页面。
               .successHandler(myAuthenticationSuccessHandler)//使用自定义的成功处理器
               .failureHandler(myAuthenticationFailureHandler)//使用自定义的失败处理器
               .and()
               .authorizeRequests()
//               .antMatchers("/login").permitAll()
               .anyRequest()
               .authenticated();
    }
}
