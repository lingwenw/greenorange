package com.wpp.greenorange.service;

import com.wpp.greenorange.domain.GoodsFavourite;
import com.wpp.greenorange.domain.GoodsSku;

import java.util.List;

/**
 * (GoodsFavourite)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:14:16
 */
public interface GoodsFavouriteService {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsFavourite 实例对象
     * @return 对象列表
     */
    List<GoodsFavourite> findAllByCondition(GoodsFavourite goodsFavourite);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsFavourite findById(Integer id);

    /**
     * 通过skuId查询单条数据
     * @param skuId
     * @return
     */
    GoodsFavourite findBySkuId(Integer skuId);

    /**
     * 通过skuId查询单条数据
     * @param id
     * @return
     */
    GoodsSku findsBySkuId(Integer id);
    /**
     * 新增数据
     *
     * @param goodsFavourite 实例对象
     * @return 是否成功
     */
    Boolean insert(GoodsFavourite goodsFavourite);

    /**
     * 修改数据
     *
     * @param goodsFavourite 实例对象
     * @return 是否成功
     */
    Boolean update(GoodsFavourite goodsFavourite);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Integer id);

}