package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Admin)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:30:17
 */
@Mapper
public interface AdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Admin findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> findAllByCondition(Admin admin);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    Integer insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    Integer update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

}