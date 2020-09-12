package com.wpp.greenorange.dao;

import com.wpp.greenorange.domain.Tb_Advert_Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface Tb_Advert_PositionDao {
    @Select("select * from tb_advert_position")
    List<Tb_Advert_Position> allAdvert_Position();
}
