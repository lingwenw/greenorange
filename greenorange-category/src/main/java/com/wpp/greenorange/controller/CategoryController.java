package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Category;
import com.wpp.greenorange.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品类目(Category)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:41:14
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Category getOne(Integer id) {
        return this.categoryService.findById(id);
    }

}