package com.wpp.greenorange.service;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Advert;

import java.util.List;

/**
 * (Advert)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:36:49
 */
public interface AdvertService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param advert 实例对象
     * @return 对象列表
     */
    List<Advert> findAllByCondition(Advert advert);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Advert findById(Integer id);

    /**
     * 新增数据
     *
     * @param advert 实例对象
     * @return 是否成功
     */
    Boolean insert(Advert advert);

    /**
     * 修改数据
     *
     * @param advert 实例对象
     * @return 是否成功
     */
    Boolean update(Advert advert);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

    PageInfo<Advert> findAllLimit(Integer pageNum,Integer pageSize);
}