package com.wpp.greenorange.service;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Order;
import com.wpp.greenorange.domain.select.OrderSelect;
import com.wpp.greenorange.domain.User;

import java.util.List;
import java.util.Map;

/**
 * (Order)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:00:16
 */
public interface OrderService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    List<Order> findAllByCondition(Order order);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order findById(Integer id);

    /**
     * 新增数据
     *
     * @param
     * @return 是否成功
     */
    Order insert(Map orderData, User user);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 是否成功
     */
    Boolean update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

    /**
     * 分页的方法
     * @param orderSelect
     * @return
     */
    PageInfo<Order> getAllLimit(OrderSelect orderSelect);

    /**
     *
     * @return
     */
    List<Map> getAllOrderStatus();
}