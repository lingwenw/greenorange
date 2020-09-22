package com.wpp.greenorange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.AdvertDao;
import com.wpp.greenorange.domain.Advert;
import com.wpp.greenorange.service.AdvertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Advert)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:36:49
 */
@Service("advertService")
public class AdvertServiceImpl implements AdvertService {
    @Resource
    private AdvertDao advertDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param advert 实例对象
     * @return 对象列表
     */
    @Override
    public List<Advert> findAllByCondition(Advert advert) {
        return this.advertDao.findAllByCondition(advert);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Advert findById(Integer id) {
        return this.advertDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param advert 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Advert advert) {
        return this.advertDao.insert(advert) > 0;
    }

    /**
     * 修改数据
     *
     * @param advert 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Advert advert) {
        return this.advertDao.update(advert) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.advertDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Advert> findAllLimit(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Advert> list = advertDao.findAllByCondition(null);
        return PageInfo.of(list,pageSize);
    }
}