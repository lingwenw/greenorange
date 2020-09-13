package com.wpp.greenorange.init;

import com.wpp.greenorange.service.CategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Init implements InitializingBean {
    @Autowired
    private CategoryService categoryService;

    public void afterPropertiesSet() throws Exception {
        System.out.println("‐‐‐缓存预热‐‐‐");
        categoryService.saveCategoryTreeToRedis();//加载商品分类导航缓存
    }
}