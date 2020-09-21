package com.wpp.greenorange.init;

import com.wpp.greenorange.service.CategoryService;
import com.wpp.greenorange.service.GoodsSkuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Init implements InitializingBean {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsSkuService goodsSkuService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("‐‐‐缓存预热‐‐‐");
        categoryService.saveCategoryTreeToRedis();//加载商品分类导航缓存
        goodsSkuService.saveSkuToRedis();
    }
}