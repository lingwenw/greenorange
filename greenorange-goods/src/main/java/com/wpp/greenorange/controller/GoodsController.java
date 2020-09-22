package com.wpp.greenorange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.select.GoodsSelect;
import com.wpp.greenorange.service.GoodsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.io.*;
import java.util.*;

/**
 * (Goods)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    @RequestMapping("/getAllLimit")
    public PageInfo<Goods> getAllLimit(GoodsSelect goodsSelect){
        return goodsService.findAllLimit(goodsSelect);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Goods getOne(Integer id) {
        return this.goodsService.findById(id);
    }



    /**
     * 将点击的新数据添加到数据库
     *
     * @param goods 对象
     * @return 布尔值
     */
    @RequestMapping("/insertGoods")
    public boolean insertGoods(Goods goods){
//        Goods goods1 = new Goods();
//        goods1.setId(1);
//        goods1.setCategoryId(560);
//        goods1.setCategoryName("一加8");
//        goods1.setName("【直降600+限量赠手机壳+以旧换新至高返1100元】90Hz高清柔性屏,骁龙865,180g轻薄手感<a href=\"#\" target=\"_blank\">上市价4599元，到手价3999元起</a>");
//        goods1.setBrandId(23);
//        goods1.setDeleted(false);
//        goods1.setCreateTime(new Date("2020-09-04 10:14:49"));
//        goods1.setUpdateTime(new Date("2020-09-08 22:13:36"));
        Boolean insert = this.goodsService.insert(goods);
        return insert;
    }

    /**
     * 将修改好的数据传入数据库中修改
     *
     * @param goods 对象
     * @return
     */
    @RequestMapping("/updateGoods")
    public boolean updateGoods(Goods goods){
        Boolean update = this.goodsService.update(goods);
        return update;
    }

}