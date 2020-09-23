package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wpp.greenorange.service.PowerService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 权限控制控制器
 * @author  吴鹏鹏pp
 */
@RestController
@RequestMapping("/power")
public class PowerController {

    @Resource
    private PowerService powerService;

    /**
     * 修改一名用户的权限
     * @return
     */
    @RequestMapping("/updateAdminPower")
    @PreAuthorize("hasAnyAuthority('empower_write','super_admin')")
    public boolean updateAdminPower(@RequestParam("powerIds[]") List<Integer> powerIds, Integer adminId){
        return powerService.updateAdminPower(powerIds, adminId);
    }

    /**
     * 获得全部管理员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAdminAndPower")
    @PreAuthorize("hasAnyAuthority('empower_read','empower_write','super_admin')")
    public PageInfo<Map<String, Object>> getAdminAndPower(Integer pageNum, Integer pageSize){
        return powerService.findAdminAndPower(pageNum, pageSize);
    }


    /**
     * 获得分配权限需要的信息
     * @return
     */
    @RequestMapping("/getAssignInfo")
    @PreAuthorize("hasAnyAuthority('empower_read','empower_write','super_admin')")
    public Map<String, Object> getAssignInfo(Integer id){

        return powerService.getAssignInfo(id);
    }
}
