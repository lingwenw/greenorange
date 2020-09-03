package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.Goods;

import java.util.List;

/**
 * (Goods)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
public interface GoodsService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goods 实例对象
     * @return 对象列表
     */
    List<Goods> findAllByCondition(Goods goods);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Goods findById(Integer id);

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 是否成功
     */
    Boolean insert(Goods goods);

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 是否成功
     */
    Boolean update(Goods goods);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

}