package com.wpp.greenorange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.select.GoodsSelect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * (Goods)表服务接口
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
public interface GoodsService {

    /**
     * 根据id生成静态页面
     * @param goodsId
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    void createPage(Integer goodsId) throws FileNotFoundException, UnsupportedEncodingException, JsonProcessingException;

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
    Boolean deleteById(Integer id) throws IOException;

    /**
     * 分页方法
     * @return
     */
    PageInfo<Goods> findAllLimit(GoodsSelect goodsSelect);

    /**
     * 获得全部品牌
     * @return
     */
    List<Map> getAllBrand();

    /**
     * 获得当前商品的分类和商品信息
     * @param goodsId
     * @return
     */
    Map<String, Object> getInfoSkuAddNeed(Integer goodsId) throws JsonProcessingException;

    Map enableGoods(Goods goods);
}