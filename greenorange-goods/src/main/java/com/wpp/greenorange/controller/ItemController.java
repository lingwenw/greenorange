package com.wpp.greenorange.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.service.GoodsService;
import com.wpp.webutil.exception.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *生成商品详情页面
 * @author 吴鹏鹏
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    private TemplateEngine templateEngine = new TemplateEngine();

    @Resource
    private GoodsService goodsService;

    @Value("${pagePath}")
    private String pagePath;


    @GetMapping("/createPage")
    public void createPage(Integer goodsId) throws FileNotFoundException, UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper json = new ObjectMapper();

        //获取sku集合
        Goods goods = goodsService.findById(goodsId);
        if (goods==null||goods.getDeleted()==true){
            throw new MyException("商品不存在或已停用");
        }
        List<GoodsSku> skuList = goods.getSkuList();

        //获取sku中的版本参数组成editionMap
        //editionMap: 商品的全部版本
        //[{"选择颜色":["金色","银色","黑色"]},{"内存容量":["16GB","64GB","黑色"]}]
        LinkedHashMap<String,Set> goodsEditionMap = new LinkedHashMap<>(8);
        Map<String,String> urlMap = new HashMap<>(8);
        //遍历skuList
        for (GoodsSku sku : skuList) {
            //获得每个sku中的版本信息
            Map<String, String> map = json.readValue(sku.getSkuEdition(), Map.class);
            //将版本信息添加到goodsEditionMap
            for (String key : map.keySet()) {
                if ( !goodsEditionMap.containsKey(key) ){
                    goodsEditionMap.put(key,new HashSet());
                }
                goodsEditionMap.get(key).add(map.get(key));
            }
            urlMap.put(sku.getSkuEdition(),sku.getId()+".html");
        }

        //生成sku页面
        for (GoodsSku sku : skuList) {
            Map<String,Set> editionMap = new LinkedHashMap<>(8);
            //当前页面的sku参数
            //{选择颜色=青空,选择版本=12GB 256GB,选择组合=【明星单品】}
            LinkedHashMap<String, String> map = json.readValue(sku.getSkuEdition(), LinkedHashMap.class);
            //改造editionMap
            //[{"选择颜色":[{"option":"金色","checked":"false"}...]]
            for (String key : goodsEditionMap.keySet()) {
                Set<String> set = goodsEditionMap.get(key);
                //创建新的set
                HashSet<Map> newSet = new LinkedHashSet<>();
                for (String value : set) {
                    Map<Object, Object> hashMap = new HashMap<>(4);
                    hashMap.put("option",value);
                    hashMap.put("checked",false);
                    //遍历当前sku的参数list，如果有这个配置的话，checked=true
                    if (value.equals(map.get(key))) {
                        hashMap.put("checked", true);
                        hashMap.put("url","javascript:void(0);");
                    }else {
                        //创建一个临时map
                        LinkedHashMap<String, String> temp = new LinkedHashMap<>(4);
                        temp.putAll(map);
                        //原map就是当前页面选中规格
                        //value是set集合中的元素，也就是与我们当前选中的规格不同的地方
                        temp.put(key,value);
                        String url = urlMap.get(json.writeValueAsString(temp));
                        hashMap.put("url",
                                url);

                    }
                    newSet.add(hashMap);
                }
                editionMap.put(key,newSet);
            }

            //创建上下文对象和模型
            Context context = new Context();
            Map<String,Object> data = new HashMap<>(16);
            //页面需要的基础属性
            data.put("goods",goods);
            data.put("sku",sku);
            data.put("editionMap",editionMap);
            data.put("imgs",json.readValue(sku.getShowImg(),List.class));
            Map params = json.readValue(sku.getParams(), Map.class);
            params.remove("屏幕尺寸");
            params.remove("电池容量");
            params.remove("分辨率");
            data.put("params",params);
            data.put("introduces",json.readValue(sku.getIntroduceData(),List.class));
            context.setVariables(data);
            //准备文件
            File dir = new File(pagePath);
            if (!dir.exists()){
                dir.mkdirs();
            }
            File dest = new File(dir, sku.getId()+".html");
            PrintWriter writer = new PrintWriter(dest,"utf-8");
            //生成页面
            templateEngine.process("item",context,writer);
            //
        }
    }
}
