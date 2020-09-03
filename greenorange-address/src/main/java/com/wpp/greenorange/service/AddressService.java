package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.Address;

import java.util.List;

/**
 * (Address)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:03:28
 */
public interface AddressService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param address 实例对象
     * @return 对象列表
     */
    List<Address> findAllByCondition(Address address);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Address findById(Integer id);

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 是否成功
     */
    Boolean insert(Address address);

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 是否成功
     */
    Boolean update(Address address);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

}