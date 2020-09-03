package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.GoodsFavourite;
import com.wpp.greenorange.service.GoodsFavouriteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (GoodsFavourite)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:14:16
 */
@RestController
@RequestMapping("/goodsFavourite")
public class GoodsFavouriteController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsFavouriteService goodsFavouriteService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public GoodsFavourite getOne(Integer id) {
        return this.goodsFavouriteService.findById(id);
    }

}