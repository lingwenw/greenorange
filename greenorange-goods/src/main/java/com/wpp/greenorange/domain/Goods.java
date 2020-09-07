package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Goods)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-06 23:13:34
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = -97445866589391238L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 商品名
     */
    private String name;
    /**
     * 卖点
     */
    private String sellPoint;
    /**
     * 销量
     */
    private Integer saleCount;
    /**
     * 参数列表
     */
    private String params;
    /**
     * 介绍图片(介绍资源)
     */
    private String introduceData;
    /**
     * 好评数
     */
    private Integer goodEvaluate;
    /**
     * 差评数
     */
    private Integer badEvaluate;
    /**
     * 最低价
     */
    private Float minPrice;
    /**
     * 是否删除（逻辑删除）
     */
    private Boolean deleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最近一次修改时间
     */
    private Date updateTime;
    /**
     * sku列表
     */
    private List<GoodsSku> skuList;

    public List<GoodsSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<GoodsSku> skuList) {
        this.skuList = skuList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIntroduceData() {
        return introduceData;
    }

    public void setIntroduceData(String introduceData) {
        this.introduceData = introduceData;
    }

    public Integer getGoodEvaluate() {
        return goodEvaluate;
    }

    public void setGoodEvaluate(Integer goodEvaluate) {
        this.goodEvaluate = goodEvaluate;
    }

    public Integer getBadEvaluate() {
        return badEvaluate;
    }

    public void setBadEvaluate(Integer badEvaluate) {
        this.badEvaluate = badEvaluate;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}