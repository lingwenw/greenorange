package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.select.GoodsSelect;
import com.wpp.greenorange.service.GoodsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public PageInfo<Goods> getAllLimit(@RequestBody GoodsSelect goodsSelect){
        System.out.println(goodsSelect);
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

}