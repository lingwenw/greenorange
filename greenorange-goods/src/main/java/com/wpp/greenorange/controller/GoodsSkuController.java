package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.select.GoodsSkuSelect;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.service.GoodsSkuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;

/**
 * (GoodsSku)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
@RestController
public class GoodsSkuController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsSkuService goodsSkuService;


    @PutMapping("/sku/addSku")
    public boolean addSku(@RequestBody GoodsSku sku) throws IOException {
        return goodsSkuService.insert(sku);
    }

    @PutMapping("/sku/enableSku")
    public boolean enableSku(@RequestBody GoodsSku sku) throws IOException {
        goodsSkuService.enableSku(sku);
        return true;
    }

    /**
     * 获得分页信息
     * @return
     */
    @GetMapping("/sku/getAllLimit")
    public PageInfo<GoodsSku> getAllLimit(GoodsSkuSelect goodsSkuSelect){
        if (goodsSkuSelect.getPageNum()==null||goodsSkuSelect.getPageNum()<1){
            goodsSkuSelect.setPageNum(1);
        }
        return goodsSkuService.getAllLimit(goodsSkuSelect);
    }

    /**
     *
     * @param input 输入框的值
     * @param brand 品牌名称
     * @param category 分类名称
     * @param pageNum 当前页码
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView search(String input, String[] brand, String category, Integer pageNum,
                               String sort, String order,String[] params, String price, HttpServletRequest req) throws IOException {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        ModelAndView modelAndView = new ModelAndView("search");
        //从es中获得数据
        Map<String, Object>  search = goodsSkuService.search(input, brand, category, pageNum, sort, order, params, price);
        //组织url
        String queryString = req.getQueryString();
        if (queryString==null){
            queryString = "";
        }
        String url = req.getRequestURL().append("?").append(queryString).toString();
        //处理page
        if (!url.contains("pageNum=")) {
            url += "&pageNum=1";
        }
        //处理sort
        if (sort==null){
            sort="";
        }
        if (!url.contains("sort=")){
            url += "&sort=null";
        }
        //处理order
        if (order==null){
            order="";
        }
        if (!url.contains("order=")){
            url += "&order=null";
        }
        //处理category
        if (category==null){
            category="";
        }
        if (!url.contains("category=")){
            url += "&category=null";
        }
        //处理价格筛选
        if (price==null){
            price="";
        }
        //处理url
        url = url.replaceAll("null","");
        url = URLDecoder.decode(url, "UTF-8");
        url = url.replaceAll("&{2,}","&");
        //前端需要的数据
        modelAndView.addObject("page",search.get("pageInfo"));
        modelAndView.addObject("params",search.get("params"));
        modelAndView.addObject("url",url);
        modelAndView.addObject("input",input==null?"":input);
        modelAndView.addObject("brands",brand);
        modelAndView.addObject("category",category);
        modelAndView.addObject("sort",sort);
        modelAndView.addObject("order",order);
        modelAndView.addObject("price",price.split("-"));
        modelAndView.addObject("inputParams",params);
        return modelAndView;
    }

    @RequestMapping("/searchJson")
    public Map<String, Object> searchJson(String input, String[] brand, String category, Integer pageNum) throws IOException {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        //从es中获得数据
//        Map<String, Object>  search = goodsSkuService.search(input, brand, category, pageNum, sort, order, params);

        return null;
    }

    /**
     * 根据id查询对应数据
     * @param id
     * @return
     */
    @RequestMapping("/GoodsSkuFindById")
    public GoodsSku findById(Integer id){
        return goodsSkuService.findById(id);
    }
}