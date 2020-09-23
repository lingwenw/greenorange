package com.wpp.greenorange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Category;
import com.wpp.greenorange.service.CategoryService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品类目(Category)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:41:14
 */
@RestController
@RequestMapping("/category")
public class CategoryController{
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


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


    /**
     * 获取所有商品分类信息
     *
     * @return tree
     * @throws JsonProcessingException
     */
    @RequestMapping("/findCategorys")
    public List<Map> findCategorys() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String categorys = redisTemplate.opsForValue().get("categorys");
        if (categorys==null){
            categoryService.saveCategoryTreeToRedis();
            categorys = redisTemplate.opsForValue().get("categorys");
        }
        return mapper.readValue(categorys, List.class);
    }

    /**
     * @param category
     * @return Category 修改数据
     */
    @RequestMapping("/updateCategory")
    @PreAuthorize("hasAnyAuthority('category_read','category_write','super_admin')")
    public boolean updateCategory(Category category) throws JsonProcessingException {
        boolean isUpdate = categoryService.update(category);
        if (isUpdate) {
            redisTemplate.delete("categorys");
            categoryService.saveCategoryTreeToRedis();
        }
        return isUpdate;
    }

    /**
     * @param category 删除数据
     * @return
     */
    @RequestMapping("/deleteCategory")
    @PreAuthorize("hasAnyAuthority('category_read','category_write','super_admin')")
    public boolean deleteCategory(Category category) throws JsonProcessingException {
        boolean aBoolean = categoryService.deleteById(category);
        redisTemplate.delete("categorys");
        categoryService.saveCategoryTreeToRedis();
        return aBoolean;
    }

    @RequestMapping("/addCategory")
    @PreAuthorize("hasAnyAuthority('category_read','category_write','super_admin')")
    public boolean addCategory(Category category) throws JsonProcessingException {
        Boolean insert = categoryService.insert(category);
        if (insert){
            redisTemplate.delete("categorys");
            categoryService.saveCategoryTreeToRedis();
        }
        return insert;
    }

}