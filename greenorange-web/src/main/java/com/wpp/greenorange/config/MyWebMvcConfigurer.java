package com.wpp.greenorange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 吴鹏鹏ppp
 * 该类用来定义外部资源位置
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置图片的外部路径
        registry.addResourceHandler("/img/**").addResourceLocations("file:E:/greenorange/img/");
    }
}