package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Advert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Advert)表数据库访问层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:36:49
 */
@Mapper
public interface AdvertDao {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Advert findById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param advert 实例对象
     * @return 对象列表
     */
    List<Advert> findAllByCondition(Advert advert);


    /**
     * 新增数据
     *
     * @param advert 实例对象
     * @return 影响行数
     */
    Integer insert(Advert advert);

    /**
     * 修改数据
     *
     * @param advert 实例对象
     * @return 影响行数
     */
    Integer update(Advert advert);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteById(Integer id);

}