package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.GoodsFavouriteDao;
import com.wpp.greenorange.domain.GoodsFavourite;
import com.wpp.greenorange.service.GoodsFavouriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GoodsFavourite)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:14:16
 */
@Service("goodsFavouriteService")
public class GoodsFavouriteServiceImpl implements GoodsFavouriteService {
    @Resource
    private GoodsFavouriteDao goodsFavouriteDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsFavourite 实例对象
     * @return 对象列表
     */
    @Override
    public List<GoodsFavourite> findAllByCondition(GoodsFavourite goodsFavourite) {
        return this.goodsFavouriteDao.findAllByCondition(goodsFavourite);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsFavourite findById(Integer id) {
        return this.goodsFavouriteDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param goodsFavourite 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(GoodsFavourite goodsFavourite) {
        return this.goodsFavouriteDao.insert(goodsFavourite) > 0;
    }

    /**
     * 修改数据
     *
     * @param goodsFavourite 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(GoodsFavourite goodsFavourite) {
        return this.goodsFavouriteDao.update(goodsFavourite) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.goodsFavouriteDao.deleteById(id) > 0;
    }
}