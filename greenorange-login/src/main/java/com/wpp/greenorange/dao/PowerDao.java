package com.wpp.greenorange.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 查询权限的dao
 * @author 吴鹏鹏ppp
 */
@Mapper
public interface PowerDao {


    /**
     * 查询网页所需权限
     * @return
     */
    @Select("SELECT\n" +
            "pr.resources resources,\n" +
            "GROUP_CONCAT( p.power_name SEPARATOR ',' ) AS powerName \n" +
            "FROM\n" +
            "tb_power_resources pr\n" +
            "LEFT JOIN tb_power p ON p.id = pr.power_id\n" +
            "WHERE\n" +
            "pr.resources != '' \n" +
            "GROUP BY\n" +
            "pr.resources")
    List<Map<String, String>> findResourcePower();
}
