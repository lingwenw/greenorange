package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Advert;
import com.wpp.greenorange.service.AdvertService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Advert)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:36:49
 */
@RestController
@RequestMapping("/advert")
public class AdvertController {
    /**
     * 服务对象
     */
    @Resource
    private AdvertService advertService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Advert getOne(Integer id) {
        return this.advertService.findById(id);
    }

}