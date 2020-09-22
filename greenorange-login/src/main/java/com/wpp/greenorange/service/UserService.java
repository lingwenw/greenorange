package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:15:45
 */
public interface UserService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> findAllByCondition(User user);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User findById(Integer id);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    Boolean insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    Boolean update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);








    /*
     * @param email 邮箱
     * 通过邮箱查询
     * @return User
     */
    User findByemail(String Email,String password);

    /*
     * @param phone 手机号
     * 通过手机号查询
     * @return User
     */
    User findByphone(String Phone,String password);

    /*
     * @param name 用户名
     * 通过用户名查询
     * @return User
     */
    User findByname(String Name,String password);

    /*
     * @user 注册时的user
     * 注册
     * return 是否添加成功
     */
    Boolean insertUser(User user);

    /*
     * @param eamil 邮箱
     * @return
     */
    boolean registerFindByemail(String email);

    /*
     * @param phone 手机
     * @return
     */
    boolean registerFindByphone(String phone);


}