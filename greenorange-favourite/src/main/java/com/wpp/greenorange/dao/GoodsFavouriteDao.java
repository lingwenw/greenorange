package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.GoodsFavourite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (GoodsFavourite)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:14:16
 */
@Mapper
public interface GoodsFavouriteDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsFavourite findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsFavourite 实例对象
     * @return 对象列表
     */
    List<GoodsFavourite> findAllByCondition(GoodsFavourite goodsFavourite);

    /**
     * 新增数据
     *
     * @param goodsFavourite 实例对象
     * @return 影响行数
     */
    Integer insert(GoodsFavourite goodsFavourite);

    /**
     * 修改数据
     *
     * @param goodsFavourite 实例对象
     * @return 影响行数
     */
    Integer update(GoodsFavourite goodsFavourite);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

}