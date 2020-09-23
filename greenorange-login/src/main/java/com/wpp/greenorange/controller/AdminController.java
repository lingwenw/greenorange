package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Admin;
import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAnyAuthority('user_read','user_write','super_admin')")
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
        //获得上下文
        SecurityContext  context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = context.getAuthentication();
        //获得管理员
        Admin principal = (Admin) authentication.getPrincipal();
        return principal;

    }

    /*
     * 解封
     *
     */
    @RequestMapping("/startUser")
    @PreAuthorize("hasAnyAuthority('user_write','super_admin')")
    public boolean startUser(int id){
        System.out.println(id);
        return adminService.startUser(id);
    }

    /*
     * 封号
     *
     */
    @RequestMapping("/StopUser")
    @PreAuthorize("hasAnyAuthority('user_write','super_admin')")
    public boolean StopUser(int id){
        System.out.println(id);
        return adminService.StopUser(id);
    }

    /*
     * 永封
     *
     */
    @RequestMapping("/foreverStop")
    @PreAuthorize("hasAnyAuthority('user_write','super_admin')")
    public boolean foreverStop(int id){
        System.out.println(id);
        return adminService.foreverStop(id);
    }
}