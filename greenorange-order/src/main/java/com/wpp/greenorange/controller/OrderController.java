package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.Order;
import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.domain.select.OrderSelect;
import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.GoodsService;
import com.wpp.greenorange.service.GoodsSkuService;
import com.wpp.greenorange.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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



    @RequestMapping("/getAllLimit")
    public PageInfo<Order> getAllLimit(OrderSelect orderSelect){
        return orderService.getAllLimit(orderSelect);
    }

    @RequestMapping("/getUserOrderLimit")
    public PageInfo<Order> getUserOrderLimit(OrderSelect orderSelect, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
//        orderSelect.setUserId( user.getId() );
        orderSelect.setUserId( 1 );
        return orderService.getAllLimit(orderSelect);
    }

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

    /**
     * 获得全部订单状态
     * @return
     */
    @GetMapping("/getAllOrderStatus")
    public List<Map> getAllOrderStatus(){
        return orderService.getAllOrderStatus();
    }

    @RequestMapping(value = "/addOrder")
    public @ResponseBody Order addOrder(@RequestBody Map orderData, HttpSession session, HttpServletRequest req) throws UnsupportedEncodingException {
        User user = (User) session.getAttribute("loginUser");
//        User user = new User();
//        user.setId(1);
        Order order = orderService.insert(orderData, user);
        Order order1 = new Order();
        order1.setId(order.getId());
        order1.setPrice(order.getPrice());
        order1.setSubject(order.getSubject());
        return order1;
    }
}