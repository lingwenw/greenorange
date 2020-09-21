package com.wpp.greenorange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.OrderDao;
import com.wpp.greenorange.domain.Order;
import com.wpp.greenorange.domain.select.OrderSelect;
import com.wpp.greenorange.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Order)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:00:16
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    @Override
    public List<Order> findAllByCondition(Order order) {
        return this.orderDao.findAllByCondition(order);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order findById(Integer id) {
        return this.orderDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Order order) {
        return this.orderDao.insert(order) > 0;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Order order) {
        return this.orderDao.update(order) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.orderDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Order> getAllLimit(OrderSelect orderSelect) {
        PageHelper.startPage(orderSelect.getPageNum(), orderSelect.getPageSize(),"create_time desc");
        List<Order> list = orderDao.findAllByCondition(orderSelect);
        return PageInfo.of(list, 5);
    }

    @Override
    public List<Map> getAllOrderStatus() {
        return orderDao.getAllOrderStatus();
    }
}