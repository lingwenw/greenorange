package com.wpp.greenorange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.PowerDao;
import com.wpp.webutil.exception.MyException;
import org.springframework.stereotype.Service;
import com.wpp.greenorange.service.PowerService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限控制服务层实现类
 * @author 吴鹏鹏ppp
 */
@Service
public class PowerServiceImpl implements PowerService {

    @Resource
    private PowerDao powerDao;


    @Override
    public PageInfo<Map<String, Object>> findAdminAndPower(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = powerDao.findAdminAndPower();
        return PageInfo.of(list,5);
    }

    @Override
    public Map<String, Object> getAssignInfo(Integer id) {
        List<Map<String, Object>> allPower = powerDao.findAllPower();
        List<Map<String, Object>> adminPower = powerDao.findPowerByAdminId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("allPower",allPower);
        map.put("adminPower",adminPower);
        Map<String,Object> admin = powerDao.findAdminByiId(id);
        admin.put("password","***********");
        map.put("admin",admin);
        return map;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, MyException.class})
    public boolean updateAdminPower(List<Integer> powerIds, Integer adminId) {
        powerDao.deletePowerByAdminId(adminId);
        System.out.println(111);
        powerDao.insertPowers(powerIds, adminId);
        return true;
    }
}
