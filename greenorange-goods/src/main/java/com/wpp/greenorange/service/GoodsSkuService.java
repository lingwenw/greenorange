package com.wpp.greenorange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.SkuEs;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * (GoodsSku)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
public interface GoodsSkuService {

//    boolean

    /**
     * 把一个sku保存到elasticsearch
     * @param sku
     */
    void saveSkuEsToElasticSearch(GoodsSku sku) throws IOException;

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

    /**
     * 处理控制器的搜索
     * @return
     */
    Map<String, Object> search(String input, String[] brand, String category, Integer pageNum) throws IOException;
}