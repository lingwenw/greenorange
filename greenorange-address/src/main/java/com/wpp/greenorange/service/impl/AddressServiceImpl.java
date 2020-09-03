package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.AddressDao;
import com.wpp.greenorange.domain.Address;
import com.wpp.greenorange.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Address)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:03:28
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressDao addressDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param address 实例对象
     * @return 对象列表
     */
    @Override
    public List<Address> findAllByCondition(Address address) {
        return this.addressDao.findAllByCondition(address);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Address findById(Integer id) {
        return this.addressDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Address address) {
        return this.addressDao.insert(address) > 0;
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Address address) {
        return this.addressDao.update(address) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.addressDao.deleteById(id) > 0;
    }
}