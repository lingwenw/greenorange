package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.Tb_Advert_Position;
import com.wpp.greenorange.service.AdvertService;
import com.wpp.greenorange.service.Tb_Advert_PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Tb_Advert_PositionController {
    @Resource
    private Tb_Advert_PositionService positionService;
    @RequestMapping("/position")
    private List getAllposition(){

        List<Tb_Advert_Position> positions = positionService.allAdvert_Position();

        return positions;
    }

}
