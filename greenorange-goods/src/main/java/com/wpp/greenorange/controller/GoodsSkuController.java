package com.wpp.greenorange.controller;

import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.SkuEs;
import com.wpp.greenorange.service.GoodsSkuService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * (GoodsSku)表控制层
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
@RestController
@RequestMapping("/goodsSku")
public class GoodsSkuController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsSkuService goodsSkuService;



    /**
     *
     * @param input 输入框的值
     * @param brand 品牌名称
     * @param category 分类名称
     * @param pageNum 当前页码
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView search(String input, String[] brand, String category, Integer pageNum, HttpServletRequest req) throws IOException {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        System.out.println(111);
        ModelAndView modelAndView = new ModelAndView("search");
        //从es中获得数据
        Map<String, Object>  search = goodsSkuService.search(input, brand, category, pageNum);
        //组织url
        String queryString = req.getQueryString();
        if (queryString==null){
            queryString = "";
        }
        String url = req.getRequestURL().append("?").append(queryString).toString();
        if (!url.contains("pageNum=")) {
            url += "&pageNum=1";
        }
        if (category==null){
            category="";
        }
        if (!url.contains("category=")){
            url += "&category=null";
        }
        url = url.replaceAll("null","");
        url = URLDecoder.decode(url, "UTF-8");
        url = url.replaceAll("&{2,}","&");
        //前端需要的数据
        modelAndView.addObject("page",search.get("pageInfo"));
        modelAndView.addObject("params",search.get("params"));
        modelAndView.addObject("url",url);
        modelAndView.addObject("input",input==null?"":input);
        modelAndView.addObject("brands",brand);
//        modelAndView.addObject("brandStr",Arrays.toString((String[]) search.get("brand")));
        modelAndView.addObject("category",category);
        return modelAndView;
    }

    @RequestMapping("/searchJson")
    public Map<String, Object> searchJson(String input, String[] brand, String category, Integer pageNum) throws IOException {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        //从es中获得数据
        Map<String, Object>  search = goodsSkuService.search(input, brand, category, pageNum);

        return search;
    }



}