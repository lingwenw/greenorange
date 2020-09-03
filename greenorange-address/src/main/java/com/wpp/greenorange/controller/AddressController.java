package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Address;
import com.wpp.greenorange.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Address)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:03:28
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Address getOne(Integer id) {
        return this.addressService.findById(id);
    }

}