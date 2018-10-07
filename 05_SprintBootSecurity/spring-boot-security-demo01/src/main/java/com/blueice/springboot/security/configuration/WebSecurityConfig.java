package com.blueice.springboot.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * WebSecurityConfigurerAdapter:重写它的方法来设置一些web的安全设置
 */
@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 表示在内存中创建一个username为admin, password为123456，role为USER的用户。
         * 但大多数web应用肯定会有自己的用户管理系统，
         * 这个我们在接下来的博客会另起一篇博客介绍，这里先用内存用户。
         */
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("USER");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /**
         *表示在内存中创建一个username为admin, password为123456，role为USER的用户。
         *但大多数web应用肯定会有自己的用户管理系统，
         *这个我们在接下来的博客会另起一篇博客介绍，这里先用内存用户。
         */
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    /**
     * 用来定义安全策略，如哪些url路径需要经过授权才能访问，哪些不用。
     * 如，”/”就不需要授权就可以访问，
     * 即我们可以正常访问https://localhost:8443/SpringBootBase/，而不需要用户授权。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()//定义哪些url不需要保护
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//表示在没有授权前，任何访问需要授权才能访问的页面都会先跳转到/login登录页面
                .defaultSuccessUrl("/welcome")//当一个用户成功登录后，即Spring Security认证成功后，我们的web应用将重定向到之前用户请求的页面，也可以客制化，使用defaultSuccessUrl方法将其重定向到指定页面。
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
}
