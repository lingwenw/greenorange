package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.GoodsSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (GoodsSku)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
@Mapper
public interface GoodsSkuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsSku findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsSku 实例对象
     * @return 对象列表
     */
    List<GoodsSku> findAllByCondition(GoodsSku goodsSku);

    /**
     * 新增数据
     *
     * @param goodsSku 实例对象
     * @return 影响行数
     */
    Integer insert(GoodsSku goodsSku);

    /**
     * 修改数据
     *
     * @param goodsSku 实例对象
     * @return 影响行数
     */
    Integer update(GoodsSku goodsSku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

}