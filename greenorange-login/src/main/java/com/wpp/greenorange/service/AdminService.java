package com.wpp.greenorange.service;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Admin;
import com.wpp.greenorange.domain.User;

import java.util.List;

/**
 * (Admin)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:30:17
 */
public interface AdminService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> findAllByCondition(Admin admin);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Admin findById(Integer id);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    Boolean insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 是否成功
     */
    Boolean update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

    /*
     * admin登录验证
     *
     */
    Admin AdminLogin(String accountNumber,String password);

    /*
     * 分页
     */
    PageInfo<User> findUserByInfo(int pageNo,int pagesize);

    /*
     * 分页
     *
     * @return 是否修改成功
     */
    boolean startUser(int id);

    /*
     * 封号
     * @return 是否修改成功
     */
    boolean StopUser(int id);

    /*
     * 永封
     * @return 是否删除成功
     */
    boolean foreverStop(int id);
}