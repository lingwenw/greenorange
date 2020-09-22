package com.wpp.greenorange.controller;

import com.wpp.greenorange.domain.GoodsFavourite;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.service.GoodsFavouriteService;
import com.wpp.greenorange.service.impl.GoodsSkuServiceImpl;
import com.wpp.greenorange.domain.User;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.security.auth.message.callback.SecretKeyCallback;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * (GoodsFavourite)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:14:16
 */
@RestController
@RequestMapping("/goodsFavourite")
public class GoodsFavouriteController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsFavouriteService goodsFavouriteService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    public GoodsFavourite getOne(Integer id) {
        return this.goodsFavouriteService.findById(id);
    }

    /**
     * 通过userID，statusID来查询购物车
     * @param goodsFavourite
     * @return
     */
    @RequestMapping("/findAllByCondition")
    public List<GoodsFavourite> findAllByCondition(GoodsFavourite goodsFavourite,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        goodsFavourite.setUserId(user.getId());
        List<GoodsFavourite> allByCondition = this.goodsFavouriteService.findAllByCondition(goodsFavourite);
        for (int i = 0; i <allByCondition.size() ; i++) {
            GoodsSku goodsSku = findsBySkuId(allByCondition.get(i).getSkuId());
            allByCondition.get(i).setGoodsSku(goodsSku);
        }
        System.out.println(allByCondition);
        return allByCondition;
    }
    /**
     * 通过id来查询购物车
     * @param ids
     * @return
     */
    @RequestMapping("/findAllByConditions")
    public List<GoodsFavourite> findAllByConditions(String ids,String nums){
        //
        List<GoodsFavourite> allByConditions=new ArrayList();
        String[] split1 = ids.split(":");
        String[] split = nums.split(":");
        for (int i = 0; i <split1.length-1 ; i++) {
            int id = Integer.parseInt(split1[i]);
            GoodsFavourite byId = this.goodsFavouriteService.findById(id);
                GoodsSku goodsSku = findsBySkuId(byId.getSkuId());
                //
                Double price = goodsSku.getPrice();
                double v = Double.parseDouble(split[i]);
                double num= price*v;
                goodsSku.setPrice(num);
                //
                byId.setGoodsSku(goodsSku);
                allByConditions.add(byId);
        }
//        System.out.println(allByConditions);
        return allByConditions;
    }
    /**
     * 根据id删除购物车
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Boolean deleteById(Integer id){
        Boolean aBoolean = this.goodsFavouriteService.deleteById(id);
        return aBoolean;
    }

    /**
     * 通过skuId查询单条数据
     *
     * @param skuId
     * @return 单条数据
     */
    @RequestMapping("/findBySkuId")
    public GoodsFavourite findBySkuId(Integer skuId){
        GoodsFavourite bySkuId = this.goodsFavouriteService.findBySkuId(skuId);
        return bySkuId;
    }
    /**
     * 通过skuId查询单条数据
     *
     * @param id
     * @return 单条数据
     */
    @RequestMapping("/findsBySkuId")
    public GoodsSku findsBySkuId(Integer id){
        GoodsSku goodsSku = this.goodsFavouriteService.findsBySkuId(id);
        return goodsSku;
    }

    @RequestMapping("/insert")
    public Boolean insert(Integer skuId,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        GoodsFavourite favourite = new GoodsFavourite();
        favourite.setSkuId(skuId);
        favourite.setUserId(user.getId());
//        favourite.setUserId(1);
        favourite.setStatusId(1);
        return goodsFavouriteService.insert(favourite);
    }
}