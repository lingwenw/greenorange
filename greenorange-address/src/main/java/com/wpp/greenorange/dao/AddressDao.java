package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Address)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:03:28
 */
@Mapper
public interface AddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Address findById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param address 实例对象
     * @return 对象列表
     */
    List<Address> findAllByCondition(Address address);

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 影响行数
     */
    Integer insert(Address address);

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 影响行数
     */
    Integer update(Address address);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

    /**
     * 通过用户名字查询id
     * @param name 用户名
     * @return id值
     */
    Integer findByuUserId(String name);

}