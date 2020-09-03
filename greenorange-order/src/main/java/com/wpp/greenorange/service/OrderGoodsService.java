package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.OrderGoods;

import java.util.List;

/**
 * (OrderGoods)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:50:30
 */
public interface OrderGoodsService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderGoods 实例对象
     * @return 对象列表
     */
    List<OrderGoods> findAllByCondition(OrderGoods orderGoods);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderGoods findById(Integer id);

    /**
     * 新增数据
     *
     * @param orderGoods 实例对象
     * @return 是否成功
     */
    Boolean insert(OrderGoods orderGoods);

    /**
     * 修改数据
     *
     * @param orderGoods 实例对象
     * @return 是否成功
     */
    Boolean update(OrderGoods orderGoods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

}