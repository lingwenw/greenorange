package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:15:45
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public User getOne(Integer id) {
        return this.userService.findById(id);
    }

}