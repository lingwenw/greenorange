package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.OrderGoods;
import com.wpp.greenorange.service.OrderGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (OrderGoods)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:50:30
 */
@RestController
@RequestMapping("/orderGoods")
public class OrderGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private OrderGoodsService orderGoodsService;

}