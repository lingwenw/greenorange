package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (Goods)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
@Mapper
public interface GoodsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Goods findById(Integer id);




    /**
     * 通过实体作为筛选条件查询
     *
     * @param goods 实例对象
     * @param ids 根据category id数组进行in查询
     * @return 对象列表
     */
    List<Goods> findAllByCondition(@Param("goods") Goods goods, @Param("ids") String ids);

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 影响行数
     */
    Integer insert(Goods goods);

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 影响行数
     */
    Integer update(Goods goods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

    /**
     * 获得全部的品牌
     * @return
     */
    List<Map> getAllBrand();
}