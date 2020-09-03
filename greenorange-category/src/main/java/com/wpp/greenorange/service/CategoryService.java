package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.Category;

import java.util.List;

/**
 * 商品类目(Category)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:41:14
 */
public interface CategoryService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    List<Category> findAllByCondition(Category category);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category findById(Integer id);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 是否成功
     */
    Boolean insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 是否成功
     */
    Boolean update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

}