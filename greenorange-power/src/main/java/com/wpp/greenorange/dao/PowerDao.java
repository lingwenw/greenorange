package com.wpp.greenorange.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 查询权限的dao
 * @author 吴鹏鹏ppp
 */
@Mapper
public interface PowerDao {
    class PowerProvider{
        public String insertPowers(List<Integer> powerIds, Integer adminId){
            StringBuffer buffer = new StringBuffer("insert into tb_admin_power(power_id,admin_id) values");
            for (Integer powerId : powerIds) {
                buffer.append("("+powerId+", "+adminId+"),");
            }
            buffer.setCharAt(buffer.length()-1,' ');
            return buffer.toString();
        };
    }

    /**
     * 根据adminId删除他的全部权限
     * @param adminId
     * @return
     */
    @Delete("delete from tb_admin_power where admin_id = #{adminId}")
    int deletePowerByAdminId(Integer adminId);

    @InsertProvider(type = PowerProvider.class,method = "insertPowers")
    int insertPowers(@Param("powerIds") List<Integer> powerIds,@Param("adminId") Integer adminId);

    @Select("select * from tb_admin where id = #{id}")
    /**
     * 根据id获得admin
     */
    Map<String, Object> findAdminByiId(Integer id);

    /**
     * 查询网页所需权限
     * @return
     */
    @Select("SELECT " +
            "pr.resources resources, " +
            "GROUP_CONCAT( p.power_name SEPARATOR ',' ) AS powerName  " +
            "FROM " +
            "tb_power_resources pr " +
            "LEFT JOIN tb_power p ON p.id = pr.power_id " +
            "WHERE " +
            "pr.resources != ''  " +
            "GROUP BY " +
            "pr.resources")
    List<Map<String, String>> findResourcePower();

    /**
     * 查询所有用户及其权限
     * @return
     */
    @Select("SELECT a.id ,a.`accountNumber`, a.`name`,  " +
            "GROUP_CONCAT( p.`comment` SEPARATOR ',' ) AS `comment`  " +
            "FROM tb_admin a " +
            "LEFT JOIN tb_admin_power ap " +
            "ON a.`id` = ap.`admin_id`  " +
            "LEFT JOIN tb_power p " +
            "ON p.`id` = ap.`power_id` " +
            "GROUP BY a.id")
    List<Map<String, Object>> findAdminAndPower();

    /**
     * 获得所有的权限
     * @return
     */
    @Select("SELECT id,power_name powerName, `comment` " +
            "FROM tb_power")
    List<Map<String, Object>> findAllPower();

    /**
     * 获取一个用户的全部权限
     * @param adminId
     * @return
     */
    @Select("SELECT power_id powerId FROM tb_admin_power WHERE admin_id = #{adminId}")
    List<Map<String, Object>> findPowerByAdminId(Integer adminId);
}
