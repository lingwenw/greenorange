package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.UserDao;
import com.wpp.greenorange.domain.User;
import com.wpp.greenorange.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:15:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @Override
    public List<User> findAllByCondition(User user) {
        return this.userDao.findAllByCondition(user);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User findById(Integer id) {
        return this.userDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(User user) {
        return this.userDao.insert(user) > 0;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(User user) {
        return this.userDao.update(user) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }










    @Override
    public User findByemail(String Email,String password) {
        return userDao.findByemail(Email,password);
    }

    @Override
    public User findByphone(String Phone,String password) {
        return userDao.findByphone(Phone,password);
    }

    @Override
    public User findByname(String Name,String password) {
        return userDao.findByname(Name,password);
    }

    @Override
    public Boolean insertUser(User user) {
        return userDao.insertUser(user)>0;
    }

    @Override
    public boolean registerFindByemail(String email) {
        return userDao.registerFindByemail(email)==1;
    }

    @Override
    public boolean registerFindByphone(String phone) {
        return userDao.registerFindByphone(phone)==1;
    }
}