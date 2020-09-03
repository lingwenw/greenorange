package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Order)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:00:16
 */
@Mapper
public interface OrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    List<Order> findAllByCondition(Order order);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    Integer insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    Integer update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

}