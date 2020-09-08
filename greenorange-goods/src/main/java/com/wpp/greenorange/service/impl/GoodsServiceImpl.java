package com.wpp.greenorange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.GoodsDao;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.select.GoodsSelect;
import com.wpp.greenorange.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Goods)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goods 实例对象
     * @return 对象列表
     */
    @Override
    public List<Goods> findAllByCondition(Goods goods) {
        return this.goodsDao.findAllByCondition(goods);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Goods findById(Integer id) {
        return this.goodsDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Goods goods) {
        return this.goodsDao.insert(goods) > 0;
    }

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Goods goods) {
        return this.goodsDao.update(goods) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.goodsDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Goods> findAllLimit(GoodsSelect goodsSelect) {
        PageHelper.startPage(goodsSelect.getPageNum(), goodsSelect.getPageSize());
        List<Goods> list = goodsDao.findAllByCondition(null);
        return PageInfo.of(list,3);
    }
}