package com.wpp.greenorange.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 *后台管理服务层接口
 * @author 吴鹏鹏pp
 */
public interface PowerService {

    /**
     * 查询所有用户及其权限
     * @return
     */
    PageInfo<Map<String, Object>> findAdminAndPower(Integer pageNum, Integer pageSize);

    /**
     * 获得分配权限需要的信息
     * "allPower" : 全部权限
     * "adminPower" : "该管理员拥有的权限"
     * @return
     */
    Map<String, Object> getAssignInfo(Integer id);

    /**
     * 更新一位管理员的权限
     * @param powerIds
     * @param adminId
     * @return
     */
    boolean updateAdminPower(List<Integer> powerIds, Integer adminId);
}
