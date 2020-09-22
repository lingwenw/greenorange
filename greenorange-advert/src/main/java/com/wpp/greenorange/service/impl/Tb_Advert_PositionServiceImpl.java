package com.wpp.greenorange.service.impl;

import com.wpp.greenorange.dao.Tb_Advert_PositionDao;
import com.wpp.greenorange.domain.Tb_Advert_Position;
import com.wpp.greenorange.service.Tb_Advert_PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("tb_Advert_PositionService")
public class Tb_Advert_PositionServiceImpl implements Tb_Advert_PositionService {

    @Resource
    private Tb_Advert_PositionDao positionDao;
    @Override
    public List<Tb_Advert_Position> allAdvert_Position() {
        return positionDao.allAdvert_Position();
    }
}
