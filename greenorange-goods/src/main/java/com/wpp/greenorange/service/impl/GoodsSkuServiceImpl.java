package com.wpp.greenorange.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.greenorange.dao.GoodsSkuDao;
import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.GoodsSku;
import com.wpp.greenorange.domain.SkuEs;
import com.wpp.greenorange.domain.select.GoodsSkuSelect;
import com.wpp.greenorange.service.CategoryService;
import com.wpp.greenorange.service.GoodsService;
import com.wpp.greenorange.service.GoodsSkuService;
import com.wpp.webutil.exception.MyException;
import com.wpp.webutil.util.MyUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * (GoodsSku)表服务实现类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 01:46:31
 */
@Service("goodsSkuService")
public class GoodsSkuServiceImpl implements GoodsSkuService {
    @Resource
    private GoodsSkuDao goodsSkuDao;

    @Resource
    private RestClientBuilder builder;

    @Resource
    private CategoryService categoryService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 搜索方法
     * @param input 用户输入的文本框
     * @param brand 商品品牌
     * @param category 商品分类
     * @param pageNum 页码
     * @param sort 排序规则
     * @param order 升降序
     * @param params 参数过滤
     * @param price 价格区间
     * @return
     * @throws IOException
     */
    @Override
    public Map<String, Object> search(String input, String[] brand, String category, Integer pageNum, String sort, String order, String[] params, String price) throws IOException {
        //封装请求对象
        RestHighLevelClient client = new RestHighLevelClient(builder);
        SearchRequest searchRequest = new SearchRequest("greenorange");
        SearchSourceBuilder searchSource = new SearchSourceBuilder();

        //排序
        if (MyUtil.isEmptyString(order)){
            order = "ASC";
        }
        if (!MyUtil.isEmptyString(sort)){
            searchSource.sort(sort, SortOrder.valueOf(order));
        }
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮字段和标签
        highlightBuilder.field("name").preTags("<font style='color:red'>").postTags("</font>");
        searchSource.highlighter(highlightBuilder);
        //判断分类或品牌是否为null
        //有空的进行分词
        boolean flag =  MyUtil.isEmptyString(category) || MyUtil.isEmptyStrArr(brand);
        //分类的map
        Map<String, Object> categoryMap = new HashMap<>(4);
        //如果用户有品牌或者分类没有输入就进行分词
        List<String> splits = new ArrayList<>();
        if ( flag && !MyUtil.isEmptyString(input) ){
            splits = splitInput(input);
        }
        //如果用户选择了分类，就将分类添加到分词集合中
        if (!MyUtil.isEmptyString(category)){
            splits.add(category);
        }
        //从数据库查用户输入的分类
        if ( splits!=null && !splits.isEmpty()){
            categoryMap = goodsSkuDao.getCategoryNameIn(splits);
            category = (String) categoryMap.get("name");
        }
        //从数据库查用户输入的品牌
        if (MyUtil.isEmptyStrArr(brand) && splits!=null && !splits.isEmpty()){
            brand = goodsSkuDao.getBrandNameIn(splits);
        }
        //构建查询
        //布尔查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //价格
        if (!MyUtil.isEmptyString(price)){
            String[] priceArr = price.split("-");
            if (priceArr.length>0 && !MyUtil.isEmptyString(priceArr[0])){
                RangeQueryBuilder gte = QueryBuilders.rangeQuery("price").gte(priceArr[0]);
                boolQuery.filter(gte);
            }
            if (priceArr.length>1 && !MyUtil.isEmptyString(priceArr[1])){
                RangeQueryBuilder lte = QueryBuilders.rangeQuery("price").lte(priceArr[0]);
                boolQuery.filter(lte);
            }
        }
        //params参数筛选
        List<String> paramNames = new ArrayList<>();
        if (!MyUtil.isEmptyStrArr(params)){
            for (String param : params) {
                String[] split = param.split("：");
                if (split.length==2){
                    boolQuery.must( QueryBuilders.termQuery("params."+split[0]+".keyword",split[1]) );
                    //聚合分组需要排除的
                    paramNames.add(split[0]);
                }
            }
        }
        //分类
        if (!MyUtil.isEmptyString(category)){
            boolQuery.must( QueryBuilders.termQuery("categoryName",category) );
            Integer categoryId = (Integer) categoryMap.get("id");
            Map<String, Object> map = categoryService.findMap(categoryId);
            List<String> aggList = this.paramTypeToAggList((String) map.get("paramType"));
            //根据分类的参数进行聚合
            for (String str : aggList) {
                //聚合时排除一下参数
                if (!paramNames.contains(str)){
                    TermsAggregationBuilder agg = AggregationBuilders.terms(str).field("params." + str + ".keyword");
                    searchSource.aggregation(agg);
                }
            }
        }
        //品牌
        if (!MyUtil.isEmptyStrArr(brand)){
            boolQuery.must( QueryBuilders.termsQuery("brandName",brand) );
        }
        //输入框
        if (!MyUtil.isEmptyString(input)){
            boolQuery.must( QueryBuilders.matchQuery("name",input) );
        }
        //分类和品牌聚合
        TermsAggregationBuilder categoryAgg = AggregationBuilders.terms("categoryNames").field("categoryName");
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brandNames").field("brandName");

        int size = 1;
        searchSource.query(boolQuery);
        searchSource.aggregation(categoryAgg);
        searchSource.aggregation(brandAgg);
        searchSource.from((pageNum-1)*size);
        searchSource.size(size);
        searchRequest.source(searchSource);

        //查询结果
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //结果中的全部aggr
        Aggregations aggregations = response.getAggregations();
        //获得查询结果
        SearchHits hits = response.getHits();

        //准备返回的结果
        Map<String, Object> map = new HashMap<>(2);
        //准备并设置pageinfo
        PageInfo<SkuEs> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setSize(size);
        pageInfo.setNavigatePages(5);
        this.getPageInfoFromEs(hits, pageInfo);
        this.setNav(pageInfo);
        map.put("brand",brand);
        map.put("pageInfo",pageInfo);
        Map<String, List> aggMap = packAggregations(aggregations);
        map.put("params",aggMap);
        return map;
    }

    /**
     * 分页方法
     * @param sku
     * @return
     */
    @Override
    public PageInfo<GoodsSku> getAllLimit(GoodsSkuSelect sku) {
        PageHelper.startPage(sku.getPageNum(), sku.getPageSize());
        List<GoodsSku> list = goodsSkuDao.findAllByCondition(sku);
        return PageInfo.of(list,5);
    }

    @Override
    public Map<String, Double> getPriceAndStock(Integer skuId) {
        Double price = (Double) redisTemplate.opsForHash().get("skuPriceStock", "price" + skuId);
        Double stock = (Double) redisTemplate.opsForHash().get("skuPriceStock", "stock" + skuId);
        Map<String, Double> map = new HashMap<>(2);
        map.put("price",price);
        map.put("stock",stock);
        return map;
    }

    @Override
    public boolean updatePriceStock(GoodsSku sku) throws IOException {
        //更新数据库
        goodsSkuDao.update(sku);
        //更新缓存
        redisTemplate.opsForHash().put("skuPriceStock","price"+sku.getId(),sku.getPrice());
        redisTemplate.opsForHash().put("skuPriceStock","stock"+sku.getId(),sku.getStock().doubleValue());
        //更新es
        this.saveSkuEsToElasticSearch(sku);
        return true;
    }

    @Override
    public Map<String, Object> getSkuInfo(Integer id, Integer goodsId) throws JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>(2);
        GoodsSku sku = goodsSkuDao.findById(id);
        map.put("sku",sku);
        Goods goods = goodsService.findById(goodsId);
        map.put("goods",goods);
        return map;
    }

    private Map<String, List > packAggregations(Aggregations agg){
        //最终返回出去的map
        Map<String, List> map = new HashMap<>();
        //存放params的数组
        List<Map> params = new ArrayList<>();
        Set<String> keySet = agg.getAsMap().keySet();
        for (String key : keySet) {
            Terms terms = agg.get(key);
            //拿到全部桶
            List<? extends Terms.Bucket> buckets = terms.getBuckets();
            List<String> temp = new ArrayList<>();
            for (Terms.Bucket bucket : buckets) {
                temp.add(bucket.getKeyAsString());
            }
            //如果是分类和品牌，直接放入map
            if ("categoryNames".equals(key) || "brandNames".equals(key)){
                map.put(key,temp);
            }else {
                //如果是params，存入tempMap
                HashMap<String, Object> tempMap = new HashMap<>(1);
                tempMap.put("paramName",key);
                tempMap.put("list",temp);
                params.add(tempMap);
            }
        }
        map.put("params",params);
        return map;
    }


    /**
     * 将paramType转为list数组
     * @param params
     * @return
     */
    private List<String> paramTypeToAggList(String params){
        List<String> list = new ArrayList<>();
        if (MyUtil.isEmptyString(params)){
            return list;
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        //json字符串转换为map
        try {
            map = mapper.readValue(params, Map.class);
        } catch (JsonProcessingException e) {
            return list;
        }
        //迭代map
        for (String key : map.keySet()) {
            if (map.get(key).equals(true)){
                list.add(key);
            }
        }
        return list;
    }

    /**
     * 将SearchResponse的查询结果转为pageInfo
     * @param hits
     * @return
     */
    private void getPageInfoFromEs(SearchHits hits, PageInfo<SkuEs> pageInfo){
        ObjectMapper mapper = new ObjectMapper();
        //设置总数据数
        long total = hits.getTotalHits().value;
        pageInfo.setTotal(total);
        //设置总页码数
        int pageCount = (int) (total%pageInfo.getSize()==0?total/pageInfo.getSize():total/pageInfo.getSize()+1);
        pageInfo.setPages(pageCount);
        //设置集合
        SearchHit[] searchHits = hits.getHits();
        List<SkuEs> list = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            SkuEs skuEs = mapper.convertValue(hit.getSourceAsMap(), SkuEs.class);
            //取出高亮
            Map<String, HighlightField> map = hit.getHighlightFields();
            if (!map.isEmpty()){
                HighlightField lightName = hit.getHighlightFields().get("name");
                skuEs.setName(lightName.getFragments()[0].toString());
            }
            list.add(skuEs);
        }
        pageInfo.setList(list);

    }

    public void setNav(PageInfo pageInfo){
        //设置页码导航
        int size = pageInfo.getNavigatePages();
        int[] navArr = new int[size];
        //如果总页数少于规定的页码数，就只展示总页数个数的页码导航
        if (pageInfo.getPages()<=pageInfo.getNavigatePages()){
            navArr = new int[pageInfo.getPages()];
            for (int i = 0; i < navArr.length; i++) {
                navArr[i] = i+1;
            }
            pageInfo.setNavigatepageNums(navArr);
            return;
        }
        int after = size/2;
        int before = size-after-1;

        int start = pageInfo.getPageNum()-before;
        int end = pageInfo.getPageNum()+after;
        if (start<1){
            start = 1;
            end = start+size-1;
        }
        if (end>pageInfo.getPages()){
            end = pageInfo.getPages();
            start = end-size+1;
        }
        for (int i = start, j = 0; i <= end; i++, j++) {
            navArr[j] = i;
        }
        pageInfo.setNavigatepageNums(navArr);
    }

    @Override
    public void saveSkuEsToElasticSearch(GoodsSku sku) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(builder);

        ObjectMapper mapper = new ObjectMapper();
        //1.将sku转为SkuEs
        SkuEs skuEs = this.goodsToSkuEs(sku);
        //2.封装请求对象
        IndexRequest indexRequest = new IndexRequest("greenorange","_doc",sku.getId().toString());
        indexRequest.source(mapper.convertValue(skuEs,Map.class));
        //存入
        client.index(indexRequest, RequestOptions.DEFAULT);
        client.close();
    }

    public List<String> splitInput(String input) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(builder);

        ObjectMapper mapper = new ObjectMapper();
        //封装请求
        Request request = new Request("GET", "_analyze");
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("analyzer", "ik_smart");
        reqMap.put("text", input);
        request.setJsonEntity(mapper.writeValueAsString(reqMap));
        //解析响应
        Response response = client.getLowLevelClient().performRequest(request);
        HttpEntity entity = response.getEntity();
        String respStr = IOUtils.toString(entity.getContent(),"utf-8");
        //将响应转为map
        Map<String,List<Map>> respMap = mapper.readValue(respStr, Map.class);
        List<Map> tokens = respMap.get("tokens");
        //返回的List
        List<String> resultList = new ArrayList<>();
        for (Map token : tokens) {
            resultList.add((String) token.get("token"));
        }
        return resultList;
    }

    /**
     * 将Sku转换为SKues
     * @param sku
     * @return
     * @throws JsonProcessingException
     */
    private SkuEs goodsToSkuEs(GoodsSku sku) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //获得goods对象
        Goods goods = goodsService.findById(sku.getGoodsId());
        //组织SkuEs对象
        SkuEs skuEs = new SkuEs();
        skuEs.setId(sku.getId().toString());
        skuEs.setGoodsId(sku.getGoodsId());
        skuEs.setPrice(sku.getPrice());
        skuEs.setName(sku.getTitle());
        //把数据库中的图片数组字符串拆出第一张图
        String oneImg = sku.getShowImg().split(",")[0];
        skuEs.setOneShowImg(oneImg.substring(2,oneImg.length()-1));
        skuEs.setCreateTime(sku.getCreateTime());
        skuEs.setCategoryName(goods.getCategoryName());
        skuEs.setBrandName(goods.getBrandName());
        skuEs.setParams(mapper.readValue(sku.getParams(), Map.class));
        skuEs.setSaleCount(sku.getSaleCount());
        skuEs.setEvaluateCount(sku.getGoodEvaluate()+sku.getBadEvaluate());
        skuEs.setSellPoint(goods.getSellPoint());
        return skuEs;
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param goodsSku 实例对象
     * @return 对象列表
     */
    @Override
    public List<GoodsSku> findAllByCondition(GoodsSku goodsSku) {
        return this.goodsSkuDao.findAllByCondition(goodsSku);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsSku findById(Integer id) {
        return this.goodsSkuDao.findById(id);
    }

    /**
     * 新增数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = {Exception.class,MyException.class})
    public Boolean insert(GoodsSku goodsSku) throws IOException {
        //获得他所属的goods
        Goods goods = goodsService.findById(goodsSku.getGoodsId());
        //新增时启用状态和goods一致
        goodsSku.setDeleted( goods.getDeleted() );
        //保存到数据库
        this.goodsSkuDao.insert(goodsSku);
        //如果商品启用中，更新静态页面
        if (!goods.getDeleted()){
            goodsService.createPage(goodsSku.getGoodsId());
            //添加到elasticsearch
            this.saveSkuEsToElasticSearch(goodsSku);
        }else{

        }
        return true;
    }

    @Override
    public boolean removeFromEs(String id) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(builder);
        DeleteRequest deleteRequest = new DeleteRequest("greenorange", id);
        client.delete(deleteRequest,RequestOptions.DEFAULT);
        return true;
    }

    /**
     * 修改数据
     *
     * @param goodsSku 实例对象
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class,MyException.class})
    public Boolean update(GoodsSku goodsSku) throws IOException {
        //更新数据库
        goodsSkuDao.update(goodsSku);
        //更新es
        this.saveSkuEsToElasticSearch(goodsSku);
        //更新静态页面
        goodsService.createPage(goodsSku.getGoodsId());

        return true;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,MyException.class})
    public Boolean enableSku(GoodsSku sku) throws IOException {
        //改变数据库
        goodsSkuDao.update(sku);
        //获得goods
        Goods goods = goodsService.findById(sku.getGoodsId());

        //停用sku
        if (sku.getDeleted()){
            //标志商品至少有一个sku启动
            boolean flag = false;
            for (GoodsSku baseSku : goods.getSkuList()) {
                if (!baseSku.getDeleted()){
                    flag = true;
                }
            }
            //如果全部sku都没有启用,停用商品
            if (!flag){
                goods.setDeleted( true );
                goodsService.enableGoods(goods);
            }else {
                //如果至少有一个sku启用
                //重新生成页面
                goodsService.createPage(goods.getId());
                //删除静态页面
//                boolean b = MyUtil.deletePage(sku.getId() + ".html");
                //删除静态页面
                MyUtil.deletePage(sku.getId()+".html");
//                System.out.println(b);
                //改变为停用，删除自己这单条es
                removeFromEs(sku.getId().toString());
            }
        }else{
            //启用sku
            //如果商品是启用状态
            if (!goods.getDeleted()){
                //重新生成页面
                goodsService.createPage(goods.getId());
                GoodsSku baseSku = goodsSkuDao.findById(sku.getId());
                //保存到es
                saveSkuEsToElasticSearch(baseSku);
            }else {
                //商品是停用状态,不做操作

            }
        }
        return true;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @param goodsId
     * @return 是否成功
     */
    @Override
    public Boolean deleteById(Integer id, Integer goodsId) throws IOException {
        Goods goods = goodsService.findById(goodsId);
        boolean flag = false;
        for (GoodsSku sku : goods.getSkuList()) {
            if (!sku.getDeleted()){
                flag = true;
            }
        }
        //该商品全部都未启用
        if (!flag){
            //停用商品
            goods.setDeleted( true );
            goodsService.enableGoods(goods);
        }else{
            //删除页面
            MyUtil.deletePage(id+".html");
            //删除es
            removeFromEs(id.toString());
        }
        //删除数据库
        this.goodsSkuDao.deleteById(id);
        return true;
    }

    @Override
    public void saveSkuToRedis() {
        Map<Object, Object> skuPriceStock = redisTemplate.opsForHash().entries("skuPriceStock");
        if (skuPriceStock==null || skuPriceStock.isEmpty()){
            //从数据库查询
            List<GoodsSku> skus = goodsSkuDao.findAllByCondition(null);
            System.out.println(skus.size());
            HashMap<String, Double> map = new HashMap<>(16);
            //添加到缓存
            for (GoodsSku sku : skus) {
                map.put("price"+sku.getId(),sku.getPrice());
                map.put("stock"+sku.getId(),sku.getStock().doubleValue());
            }
            System.out.println(map.size());
            redisTemplate.opsForHash().putAll("skuPriceStock",map);
        }
    }

}