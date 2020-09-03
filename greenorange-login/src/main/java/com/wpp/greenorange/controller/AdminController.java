package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Admin;
import com.wpp.greenorange.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:30:17
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public Admin getOne(Integer id) {
        return this.adminService.findById(id);
    }

}