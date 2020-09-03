package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.GoodsSkuDao;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.service.GoodsSkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GoodsSku)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
@Service("goodsSkuService")
public class GoodsSkuServiceImpl implements GoodsSkuService {
    @Resource
    private GoodsSkuDao goodsSkuDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsSku 实例对象
     * @return 对象列表
     */
    @Override
    public List<GoodsSku> findAllByCondition(GoodsSku goodsSku) {
        return this.goodsSkuDao.findAllByCondition(goodsSku);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsSku findById(Integer id) {
        return this.goodsSkuDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(GoodsSku goodsSku) {
        return this.goodsSkuDao.insert(goodsSku) > 0;
    }

    /**
     * 修改数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(GoodsSku goodsSku) {
        return this.goodsSkuDao.update(goodsSku) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.goodsSkuDao.deleteById(id) > 0;
    }
}