package com.wpp.greenorange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Category;
import com.wpp.greenorange.service.CategoryService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
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
public class CategoryController implements ApplicationRunner {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findCategoryLimit")
    public PageInfo<Category> findCategoryLimit(Integer pageNum, Integer pageSize) {
        return categoryService.findCategoryLimit(pageNum, pageSize);
    }


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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map> categorys = categoryService.findCategorys();
        String categorysStr = mapper.writeValueAsString(categorys);
        redisTemplate.opsForValue().set("categorys",categorysStr);
    }

    @RequestMapping("/findCategorys")
    public List<Map> findCategorys() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String categorys = redisTemplate.opsForValue().get("categorys");
        return mapper.readValue(categorys, List.class);
    }

}