package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品类目(Category)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:41:14
 */
@Mapper
public interface CategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    List<Category> findAllByCondition(Category category);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    Integer insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    Integer update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

    /**
     * 级联查询所有数据
     * @param id
     * @return
     */
    List<Category> findCategorys(Integer id);

}