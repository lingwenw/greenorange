package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.OrderGoodsDao;
import com.wpp.greenorange.domain.OrderGoods;
import com.wpp.greenorange.service.OrderGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderGoods)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:50:30
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService {
    @Resource
    private OrderGoodsDao orderGoodsDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderGoods 实例对象
     * @return 对象列表
     */
    @Override
    public List<OrderGoods> findAllByCondition(OrderGoods orderGoods) {
        return this.orderGoodsDao.findAllByCondition(orderGoods);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderGoods findById(Integer id) {
        return this.orderGoodsDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param orderGoods 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(OrderGoods orderGoods) {
        return this.orderGoodsDao.insert(orderGoods) > 0;
    }

    /**
     * 修改数据
     *
     * @param orderGoods 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(OrderGoods orderGoods) {
        return this.orderGoodsDao.update(orderGoods) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.orderGoodsDao.deleteById(id) > 0;
    }
}