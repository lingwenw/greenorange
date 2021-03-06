package com.wpp.greenorange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.select.GoodsSelect;
import com.wpp.greenorange.service.GoodsService;
import org.apache.commons.io.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wpp.webutil.util.MyUtil;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * (Goods)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    /**
     * 文件上传的方法
     * @param file
     * @return
     */
    @RequestMapping("/uploadGoods")
    @PreAuthorize("hasAnyAuthority('goods_write','super_admin')")
    public Map uploadGoods(MultipartFile file){
        HashMap<Object, Object> map = new HashMap<>(4);
        //获得文件名和目标文件夹名
        String fileName = file.getOriginalFilename();
        String filePath = MyUtil.getYmlParam("application","imgPath")+"goods_img";

        File dest =  new File(filePath,fileName);
        try {
            //标志磁盘是否有一模一样的文件
            boolean flag = false;
            //判断磁盘上是否有同名文件
            if (dest.exists()){
                //判断内容是否一致
                flag = MyUtil.byteContentEquals(file.getBytes(), FileUtils.readFileToByteArray(dest));
                //一致不上传，不一致重命名
                if (!flag){
                    fileName = UUID.randomUUID().toString().replaceAll("-","")+fileName;
                    dest = new File(filePath, fileName);
                }
            }
            if (!flag){
                file.transferTo(dest);
            }
            map.put("error",false);
            map.put("data",fileName);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",true);
            map.put("message","上传失败!");
        }
        return map;
    }


    @DeleteMapping("/deleteGoods")
    @PreAuthorize("hasAnyAuthority('goods_write','super_admin')")
    public boolean deleteGoods(Integer id) throws IOException {
        return goodsService.deleteById(id);
    }

    @PutMapping("/updateGoods")
    @PreAuthorize("hasAnyAuthority('goods_write','super_admin')")
    public Boolean updateGoods(@RequestBody Goods goods){
        return goodsService.update(goods);
    }


    /**key      :  value
     * category :  当前商品的分类对象
     * goods    :   当前商品
     * @return
     */
    @GetMapping("/getInfoSkuAddNeed")
    @PreAuthorize("hasAnyAuthority('goods_read','goods_write','super_admin')")
    public Map<String, Object> getInfoSkuAddNeed(Integer goodsId) throws JsonProcessingException {
        return goodsService.getInfoSkuAddNeed(goodsId);
    }

    /**
     * 启用或停用商品
     * @param goods
     * @return
     */
    @PutMapping("/enableGoods")
    @PreAuthorize("hasAnyAuthority('goods_write','super_admin')")
    public Map enableGoods(@RequestBody Goods goods){
        return goodsService.enableGoods(goods);
    }

    /**
     * 新增一件商品，默认停用
     * @param goods
     * @return
     */
    @RequestMapping("/addGoods")
    @PreAuthorize("hasAnyAuthority('goods_write','super_admin')")
    public boolean addGoods(Goods goods){
        goods.setDeleted(true);
        return goodsService.insert(goods);
    }

    /**
     * 获得分页数据
     * @param goodsSelect
     * @return
     */
    @RequestMapping("/getAllLimit")
    @PreAuthorize("hasAnyAuthority('goods_read','goods_write','super_admin')")
    public PageInfo<Goods> getAllLimit(GoodsSelect goodsSelect){
        return goodsService.findAllLimit(goodsSelect);
    }

    /**
     * 获得全部品牌信息
     * @return
     */
    @RequestMapping("/getAllBrand")
    @PreAuthorize("hasAnyAuthority('goods_read','super_admin')")
    public List<Map> getAllBrand(){
        return goodsService.getAllBrand();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/getOne")
    @PreAuthorize("hasAnyAuthority('goods_read','super_admin')")
    public Goods getOne(Integer id) {
        return this.goodsService.findById(id);
    }



}