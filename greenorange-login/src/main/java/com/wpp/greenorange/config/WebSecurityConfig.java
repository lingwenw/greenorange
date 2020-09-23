package com.wpp.greenorange.config;

import com.wpp.greenorange.dao.PowerDao;
import com.wpp.greenorange.service.AdminService;
import com.wpp.greenorange.service.impl.AdminServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Resource
    private PowerDao powerDao;

    // 自定义认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置不走SpringSecurity验证的url
        web.ignoring().antMatchers("/","/admin/**/*.css","/admin/**/*.js","/admin/**/*.png","/admin/**/*.ttf","/admin/**/*.woff");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //对网页资源进行控制
        List<Map<String, String>> resourcePower = powerDao.findResourcePower();
        for (Map<String, String> map : resourcePower) {
            String powerName = map.get("powerName");
            powerName += ",super_admin";
            http.authorizeRequests()
                    .antMatchers(map.get("resources")).hasAnyAuthority(powerName.split(","));
        }


        http.authorizeRequests()
                //admin下面的资源需要ADMIN权限
                .antMatchers("/admin/**").hasAnyAuthority("admin","super_admin")
                .and()
                // 同源头，避免框架集无法打开
                .headers().frameOptions().sameOrigin()
                .and()
                //设置session超时管理
                .sessionManagement()
                .invalidSessionUrl("/admin/login.html")
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                //配置登录页面
                .formLogin().loginPage("/admin/login.html")
                .loginProcessingUrl("/admin/AdminLogin")
                // 登陆成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                // 登陆失败处理器
                .failureHandler(myAuthenctiationFailureHandler)
                .permitAll();
//                .and()
                //设置userDetailsService，处理用户信息
//                .userDetailsService(adminService);
        http.headers().cacheControl(); //禁用缓存
        http.csrf().disable(); //禁用csrf校验


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
