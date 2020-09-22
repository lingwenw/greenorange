package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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

    @RequestMapping("/exit")
    public boolean exit(HttpSession session){
        session.removeAttribute("loginUser");
        return true;
    }


    @RequestMapping("/findByemail")
    public boolean findByemail(String email){
        boolean b = userService.registerFindByemail(email);
        System.out.println(b);
        return b;
    }

    @RequestMapping("/findByphone")
    public boolean findByphone(String phone){
        boolean b = userService.registerFindByphone(phone);
        System.out.println(b);
        return b;
    }

    @RequestMapping("/findByIDemailOrUserNameOrphone")
    public boolean findByIDemailOrUserNameOrphone(
                                               User user,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("登录的user:"+user);
        User byUser = userService.findByemail(user.getEmail(), user.getPassword());
        session.setAttribute("loginUser",byUser);

        System.out.println("数据库查的user:"+byUser);

        if (byUser!=null){
            boolean flag = user.getPassword().equals(byUser.getPassword());
            System.out.println(flag);
            return flag;
        }else {
            return false;
        }

    }

    @RequestMapping("/getUserLoginName")
    public User getUserLoginName(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        user.setPassword("****");
        return user;
    }

    @RequestMapping("/insertUser")
    public boolean insertUser(User user){

        User registerUser=new User();
        boolean flag=true;
        boolean registerFlag = userService.registerFindByemail(user.getEmail());
        if (registerFlag==true){
            flag=false;
            return flag;
        }
        boolean registerFlag2 = userService.registerFindByphone(user.getPhone());
        if (registerFlag2==true ){
            flag=false;
            return flag;
        }
        registerUser.setPhone(user.getPhone());
        registerUser.setPassword(user.getPassword());
        registerUser.setEmail(user.getEmail());
        registerUser.setName(user.getName());
        registerUser.setBirthday(new Date());
        registerUser.setCreateTime(new Date());
        registerUser.setUpdateTime(new Date());
        return userService.insertUser(registerUser);
    }
}