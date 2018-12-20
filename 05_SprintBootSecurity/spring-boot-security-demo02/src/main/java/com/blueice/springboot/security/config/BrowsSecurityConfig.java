package com.blueice.springboot.security.config;

import com.blueice.springboot.security.authentication.MyAuthenticationFailureHandler;
import com.blueice.springboot.security.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
//       http.formLogin()
////               .loginPage("/login")//表示在没有授权前，任何访问需要授权才能访问的页面都会先跳转到/login登录页面
//               //.defaultSuccessUrl("/welcome")//当一个用户成功登录后，即Spring Security认证成功后，我们的web应用将重定向到之前用户请求的页面，也可以客制化，使用defaultSuccessUrl方法将其重定向到指定页面。
////               .successHandler(myAuthenticationSuccessHandler)//使用自定义的成功处理器
////               .failureHandler(myAuthenticationFailureHandler)//使用自定义的失败处理器
//               .and()
//               .authorizeRequests()
//               .antMatchers("/login").permitAll()
//               .antMatchers(
//                       HttpMethod.GET, //指定请求方式
//                       "/user/*" //使用*号通配路径
////               ).hasRole("user") //指定什么权限可以访问。
////               ).hasAnyAuthority("user") //指定什么权限可以访问。
//               ).access("hasAnyAuthority('user') and hasIpAddress('127.0.0.1')") //多条件匹配的写法.
//               .anyRequest()
//               .authenticated();

        // 表示所有的访问都必须进行认证处理后才可以正常进行
        http.httpBasic().and().authorizeRequests().anyRequest()
                .fullyAuthenticated();
        // 所有的Rest服务一定要设置为无状态，以提升操作性能
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
