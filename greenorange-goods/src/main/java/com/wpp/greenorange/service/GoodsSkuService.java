package com.wpp.greenorange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.select.GoodsSkuSelect;

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
    Boolean insert(GoodsSku goodsSku) throws IOException;

    /**
     * 修改数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    Boolean update(GoodsSku goodsSku) throws IOException;

    boolean removeFromEs(String id) throws IOException;

    /**
     *启用或禁用sku
     * @param sku
     * @return
     */
    Boolean enableSku(GoodsSku sku) throws IOException;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @param goodsId
     * @return 是否成功
     */
    Boolean deleteById(Integer id, Integer goodsId) throws IOException;

    /**
     *把sku保存进redis
     */
    void saveSkuToRedis();

    /**
     * 处理控制器的搜索
     * @return
     */
    Map<String, Object> search(String input, String[] brand, String category, Integer pageNum, String sort, String order, String[] params, String price) throws IOException;

    /***
     * 分页的方法
     * @param sku
     * @return
     */
    PageInfo<GoodsSku> getAllLimit(GoodsSkuSelect sku);

    /**
     * 从redis中获取价格和库存
     * @param skuId
     * @return
     */
    Map<String, Object> getPriceAndStock(Integer skuId);

    /**
     * 更新库存和价格
     * @param sku
     * @return
     */
    boolean updatePriceStock(GoodsSku sku) throws IOException;

    /**
     * 获得该sku的信息
     * @param id
     * @param goodsId
     * @return
     */
    Map<String, Object> getSkuInfo(Integer id, Integer goodsId) throws JsonProcessingException;
}