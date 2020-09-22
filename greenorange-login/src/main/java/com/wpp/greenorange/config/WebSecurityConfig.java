package com.wpp.greenorange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationProvider myAuthenticationProvider;

    @Resource
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Resource
    //密码加密
    private MyUserDetailService myUserDetailService;

    // 自定义认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/admin/**/*.css","/admin/**/*.js","/admin/**/*.png","/admin/**/*.ttf","/admin/**/*.woff").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                // 同源头
                .headers().frameOptions().sameOrigin()
                .and()
                //配置登录页面
                .formLogin().loginPage("/admin/login.html")
                .loginProcessingUrl("/admin/AdminLogin")
                // 登陆成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                // 登陆失败处理器
                .failureHandler(myAuthenctiationFailureHandler)
                .permitAll()
                .and()
                //设置userDetailsService，处理用户信息
                .userDetailsService(myUserDetailService);

        http.headers().cacheControl(); //禁用缓存
        http.csrf().disable(); //禁用csrf校验
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
