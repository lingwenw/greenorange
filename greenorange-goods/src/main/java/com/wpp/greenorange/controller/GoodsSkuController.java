package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.service.GoodsSkuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (GoodsSku)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
@RestController
@RequestMapping("/goodsSku")
public class GoodsSkuController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsSkuService goodsSkuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public GoodsSku getOne(Integer id) {
        return this.goodsSkuService.findById(id);
    }

}