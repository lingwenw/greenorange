package com.wpp.greenorange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.AdminDao;
import com.wpp.greenorange.domain.Admin;
import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:30:17
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    @Override
    public List<Admin> findAllByCondition(Admin admin) {
        return this.adminDao.findAllByCondition(admin);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Admin findById(Integer id) {
        return this.adminDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Admin admin) {
        return this.adminDao.insert(admin) > 0;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Admin admin) {
        return this.adminDao.update(admin) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.adminDao.deleteById(id) > 0;
    }

    /*
     * admin登录验证
     *
     */
    @Override
    public Admin AdminLogin(String accountNumber, String password) {
        return adminDao.AdminLogin(accountNumber,password);
    }

    @Override
    public PageInfo<User> findUserByInfo(int pageNo,int pagesize) {
        PageHelper.startPage(pageNo,pagesize);
        List<User> l = adminDao.findUserByInfo();
        PageInfo<User> userPageInfo = new PageInfo<>(l);
        return userPageInfo;
    }

    @Override
    public boolean startUser(int id) {
        int i = adminDao.startUser(id);
        return i==1;
    }

    @Override
    public boolean StopUser(int id) {
        int i = adminDao.StopUser(id);
        return i==1;
    }

    @Override
    public boolean foreverStop(int id) {
        int i = adminDao.foreverStop(id);
        return i==1;
    }
}