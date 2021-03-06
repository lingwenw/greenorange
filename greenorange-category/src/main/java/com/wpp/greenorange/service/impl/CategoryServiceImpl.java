package com.wpp.greenorange.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.CategoryDao;
import com.wpp.greenorange.domain.Category;
import com.wpp.greenorange.service.CategoryService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类目(Category)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:41:14
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    @Override
    public List<Category> findAllByCondition(Category category) {
        return this.categoryDao.findAllByCondition(category);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category findById(Integer id) {
        return this.categoryDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Category category) {
        return this.categoryDao.insert(category) > 0;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Category category) {
        return this.categoryDao.update(category) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param category
     * @return 是否成功
     */

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Boolean deleteById(Category category) {
        categoryDao.deleteById(category.getId());
        categoryDao.updateParentId(category.getId(), category.getParentId());
        return true;
    }

    @Override
    public PageInfo<Category> findCategoryLimit(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Category category = new Category();
        category.setDeleted(true);
        List<Category> list = categoryDao.findAllByCondition(category);
        return PageInfo.of(list, 3);
    }

    /*获取商品分类数据*/
    @Override
    public List<Map> findCategorys() {
        List<Category> categories = categoryDao.findAllByCondition(null);
        List<Map> test = recursionAll(categories, 0);
        return test;
    }

    /*加载缓存数据*/
    @Override
    public void saveCategoryTreeToRedis() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String categorysStr = redisTemplate.opsForValue().get("categorys");
        if (categorysStr == null) {
            List<Map> categorys = findCategorys();
            categorysStr = mapper.writeValueAsString(categorys);
            redisTemplate.opsForValue().set("categorys", categorysStr);
        }
    }

    @Override
    public Map findMap(Integer id) throws JsonProcessingException {
        String categorysStr = redisTemplate.opsForValue().get("categorys");
        ObjectMapper mapper = new ObjectMapper();
        List<Map> list = mapper.readValue(categorysStr, List.class);
        Map map = recursionOne(list, id);
        return map;
    }

    @Override
    public String findCidsByCid(Integer categoryId) {
        return categoryDao.findCidsByCid(categoryId);
    }


    private List<Map> recursionAll(List<Category> categories, int pid) {
        List<Map> list = new ArrayList<>();
        for (Category category : categories) {
            if (category.getParentId() == pid) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put("id",category.getId());
                map.put("pId",category.getParentId());
                map.put("name", category.getName());
                map.put("paramType",category.getParamType());
                map.put("children", recursionAll(categories, category.getId()));
                list.add(map);
            }
        }
        return list;

    }

    private Map recursionOne(List<Map> list, int id) {
        Map categoryMap = new HashMap<>();
        for (Map map : list) {
            Integer this_id = (Integer) map.get("id");
            if (this_id == id) {
                return map;
            } else {
                List<Map> children = (List<Map>) map.get("children");
                categoryMap = recursionOne(children, id);
                if (categoryMap != null) {
                    return categoryMap;
                }
            }
        }
        return null;
    }


}