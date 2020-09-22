package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.OrderGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (OrderGoods)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:50:29
 */
@Mapper
public interface OrderGoodsDao {

    /**
     * 根据订单号查出订单商品
     * @param orderId
     * @return
     */
    List<OrderGoods> findByOrderId(Integer orderId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderGoods findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderGoods 实例对象
     * @return 对象列表
     */
    List<OrderGoods> findAllByCondition(OrderGoods orderGoods);

    /**
     * 新增数据
     *
     * @param orderGoods 实例对象
     * @return 影响行数
     */
    Integer insert(OrderGoods orderGoods);

    /**
     * 修改数据
     *
     * @param orderGoods 实例对象
     * @return 影响行数
     */
    Integer update(OrderGoods orderGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

}