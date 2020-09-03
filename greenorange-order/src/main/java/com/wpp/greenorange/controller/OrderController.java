package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Order;
import com.wpp.greenorange.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Order)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:00:16
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Order getOne(Integer id) {
        return this.orderService.findById(id);
    }

}