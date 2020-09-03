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

}