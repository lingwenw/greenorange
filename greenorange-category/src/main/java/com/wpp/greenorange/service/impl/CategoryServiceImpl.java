package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.CategoryDao;
import com.wpp.greenorange.domain.Category;
import com.wpp.greenorange.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.categoryDao.deleteById(id) > 0;
    }
}