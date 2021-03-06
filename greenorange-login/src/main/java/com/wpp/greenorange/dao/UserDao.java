package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:15:45
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> findAllByCondition(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    Integer insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    Integer update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);














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


    /**
     * 添加普通用户
     *
     * @param user 实体注册user
     * @return 影响行数
     */
    int insertUser(User user);

    /*
     * @param eamil 邮箱
     * @return
     */
    int registerFindByemail(String email);

    /*
     * @param phone 手机
     * @return
     */
    int registerFindByphone(String phone);
}