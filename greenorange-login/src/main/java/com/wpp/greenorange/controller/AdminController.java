package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Admin;
import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    /*
     * admin登录验证
     *
     */
    @RequestMapping("/AdminLogin")
    public boolean AdminLogin(Admin admin, HttpSession session){
//        System.out.println(admin);
//        Admin SQLadmin = adminService.AdminLogin(admin.getAccountnumber(), admin.getPassword());
//        if (SQLadmin!=null){
//            session.setAttribute("loginAdmin",SQLadmin);
//            return true;
//        }else {
//            return false;
//        }
        return true;
    }
    /*
     * 分页
     *
     */
    @RequestMapping("/findUserByInfo")
    public PageInfo<User> findUserByInfo(int pageNo, int pagesize){
        PageInfo<User> userByInfo = adminService.findUserByInfo(pageNo, pagesize);
        return userByInfo;
    }
    /*
     * 获取sisson
     *
     */
    @RequestMapping("/getLoginName")
    public Admin getLoginName(HttpSession session){
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        System.out.println(admin);
        return admin;

    }

    /*
     * 解封
     *
     */
    @RequestMapping("/startUser")
    public boolean startUser(int id){
        System.out.println(id);
        return adminService.startUser(id);
    }

    /*
     * 解封
     *
     */
    @RequestMapping("/StopUser")
    public boolean StopUser(int id){
        System.out.println(id);
        return adminService.StopUser(id);
    }

    /*
     * 永封
     *
     */
    @RequestMapping("/foreverStop")
    public boolean foreverStop(int id){
        System.out.println(id);
        return adminService.foreverStop(id);
    }
}