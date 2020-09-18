package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.GoodsDao;
import com.wpp.greenorange.dao.GoodsSkuDao;
import com.wpp.greenorange.dao.OrderDao;
import com.wpp.greenorange.dao.OrderGoodsDao;
import com.wpp.greenorange.domain.*;
import com.wpp.greenorange.service.GoodsService;
import com.wpp.greenorange.service.GoodsSkuService;
import com.wpp.greenorange.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private OrderGoodsDao orderGoodsDao;

    @Resource
    private GoodsSkuDao goodsSkuDao;

    @Resource
    private GoodsDao goodsDao;

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
     * @param
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Order insert(Map orderData,User user) {
        //订单表
        List<Map> skus = (List<Map>) orderData.get("skus");
        Double price = Double.valueOf(0);
        String subject = "";
        for (Map map : skus) {
            Integer skuId = (Integer) map.get("id");
            Integer count = (Integer) map.get("count");
            GoodsSku sku = goodsSkuDao.findById(skuId);
            price += (sku.getPrice()*count);
            Goods goods = goodsDao.findById(sku.getGoodsId());
            subject += goods.getName() + ",";
        }
        subject = subject.substring(0, subject.length() - 1);
        Integer addressId = (Integer) orderData.get("addressId");
        Order order = new Order(user.getId(), addressId, 1, price,subject);
        orderDao.insert(order);

        //订单商品表
        for (Map map : skus) {
            Integer skuId = (Integer) map.get("id");
            Integer count = (Integer) map.get("count");
            GoodsSku sku = goodsSkuDao.findById(skuId);
            OrderGoods orderGoods = new OrderGoods(skuId,sku.getPrice(),sku.getPrice(),order.getId(),order.getStatusId(),count);
            orderGoodsDao.insert(orderGoods);
        }


        return order;
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
}