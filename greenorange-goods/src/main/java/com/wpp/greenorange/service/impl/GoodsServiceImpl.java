package com.wpp.greenorange.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.GoodsDao;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.select.GoodsSelect;
import com.wpp.greenorange.service.CategoryService;
import com.wpp.greenorange.service.GoodsService;
import com.wpp.greenorange.service.GoodsSkuService;
import com.wpp.webutil.exception.MyException;
import com.wpp.webutil.util.MyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * (Goods)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:44:29
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsSkuService goodsSkuService;

    /**
     * 生成静态页面的对象
     */
    @Resource
    private TemplateEngine templateEngine = new TemplateEngine();

    /**
     * 生成静态页面的方法
     * @param goodsId
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    @Override
    public void createPage(Integer goodsId) throws FileNotFoundException, UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper json = new ObjectMapper();
        String pagePath = MyUtil.getYmlParam("application","pagePath");
        //获取sku集合
        Goods goods = this.findById(goodsId);
        if (goods==null||goods.getDeleted()==true){
            throw new MyException("商品不存在或已停用");
        }
        List<GoodsSku> skuList = goods.getSkuList();

        //获取sku中的版本参数组成editionMap
        //editionMap: 商品的全部版本
        //[{"选择颜色":["金色","银色","黑色"]},{"内存容量":["16GB","64GB","黑色"]}]
        LinkedHashMap<String, Set> goodsEditionMap = new LinkedHashMap<>(8);
        Map<String,String> urlMap = new HashMap<>(8);

        //排除停用的sku
        for (int i = 0; i < skuList.size(); i++) {
            if (skuList.get(i).getDeleted()){
                skuList.remove(i);
                i--;
            }
        }
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
            data.put("introduces",sku.getIntroduceData());
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
            writer.close();
        }
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goods 实例对象
     * @return 对象列表
     */
    @Override
    public List<Goods> findAllByCondition(Goods goods) {
        return this.goodsDao.findAllByCondition(goods,null);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Goods findById(Integer id) {
        return this.goodsDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean insert(Goods goods) {
        return this.goodsDao.insert(goods) > 0;
    }

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 是否成功
     */
    @Override
    public Boolean update(Goods goods) {

        return this.goodsDao.update(goods) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = {Exception.class,MyException.class})
    public Boolean deleteById(Integer id) throws IOException {
        //删除sku
        Goods goods = goodsDao.findById(id);
        for (GoodsSku sku : goods.getSkuList()) {
            //删除sku
            goodsSkuService.deleteById(sku.getId(), sku.getGoodsId());
        }
        //删除goods
        this.goodsDao.deleteById(id);
        return true;
    }

    @Override
    public PageInfo<Goods> findAllLimit(GoodsSelect goodsSelect) {
        String cids = null;
        //获取该分类的全部子分类cid
        if (goodsSelect.getCategoryId()!=null){
            cids = categoryService.findCidsByCid(goodsSelect.getCategoryId());
        }
        PageHelper.startPage(goodsSelect.getPageNum(), goodsSelect.getPageSize());
        List<Goods> list = goodsDao.findAllByCondition(goodsSelect,cids);
        return PageInfo.of(list,3);
    }

    @Override
    public List<Map> getAllBrand() {
        return goodsDao.getAllBrand();
    }

    @Override
    public Map<String, Object> getInfoSkuAddNeed(Integer goodsId) throws JsonProcessingException {
        //获得信息
        Goods goods = goodsDao.findById(goodsId);
        Map map = categoryService.findMap(goods.getCategoryId());
        //返回的map对象
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("category",map);
        hashMap.put("goods",goods);
        return hashMap;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,MyException.class})
    public Map enableGoods(Goods goods) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("success",true);
        //从数据库中查出对应的goods
        Goods baseGoods = goodsDao.findById(goods.getId());
        //启用
        if (!goods.getDeleted()){
            //标志是否有一个sku启用
            boolean flag = false;
            for (int i = 0; i < baseGoods.getSkuList().size(); i++) {
                if ( !baseGoods.getSkuList().get(i).getDeleted() ){
                    flag = true;
                    break;
                }
            }
            //一个sku都没有启用
            if (!flag){
                map.put("success",false);
                map.put("message","该商品没有启用的sku!请至少启用一个sku");
                return map;
            }
            //如果有sku启用了
            //更新数据库
            goodsDao.update(goods);
            //渲染静态页面
            try {
                this.createPage(goods.getId());
            } catch (Exception e) {
                throw new MyException("生成静态页面时出错!");
            }
            //添加数据到es
            for (GoodsSku sku : baseGoods.getSkuList()) {
                if (!sku.getDeleted()){
                    try {
                        goodsSkuService.saveSkuEsToElasticSearch(sku);
                    } catch (IOException e) {
                        throw new MyException("添加数据到es时出错");
                    }
                }
            }
            map.put("message","启用成功");
            return map;
        }else{
            //停用
            //更新数据库
            goodsDao.update(goods);
            for (GoodsSku sku : baseGoods.getSkuList()) {
                //删除静态页面
                MyUtil.deletePage(sku.getId()+".html");
                //删除es
                try {
                    goodsSkuService.removeFromEs(sku.getId().toString());
                } catch (IOException e) {
                    throw new MyException("删除es中的数据时出错");
                }
            }
            map.put("message","停用成功");
            return map;
        }
    }
}