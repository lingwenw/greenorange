package com.wpp.greenorange.controller;
import com.wpp.greenorange.domain.Advert;
import com.wpp.greenorange.service.AdvertService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    /**
     * 根据PositionId查询
     * @param PositionId
     * @return
     */
    @RequestMapping("/getAllByPositionId")
    public List getAllByPid(Integer PositionId) {
        Advert advert=new Advert();
        advert.setPositionId(PositionId);
        List<Advert> allByCondition = advertService.findAllByCondition(advert);
        return allByCondition;
    }
    /**
     *
     * @return查询全部数据返回页面
     */
    @RequestMapping("/getAllAdvert")
    public List getAllAdvert() {
        List<Advert> adverts = advertService.findAllByCondition(null);
        return adverts;
    }

}