package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.GoodsSku;

import java.util.List;

/**
 * (GoodsSku)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
public interface GoodsSkuService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsSku 实例对象
     * @return 对象列表
     */
    List<GoodsSku> findAllByCondition(GoodsSku goodsSku);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsSku findById(Integer id);

    /**
     * 新增数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    Boolean insert(GoodsSku goodsSku);

    /**
     * 修改数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    Boolean update(GoodsSku goodsSku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

}