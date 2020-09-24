package com.wpp.greenorange.init;

import com.wpp.greenorange.service.CategoryService;
import com.wpp.greenorange.service.GoodsSkuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Init implements InitializingBean {
    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsSkuService goodsSkuService;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("‐‐‐缓存预热‐‐‐");
        categoryService.saveCategoryTreeToRedis();//加载商品分类导航缓存
        goodsSkuService.saveSkuToRedis();
    }
}