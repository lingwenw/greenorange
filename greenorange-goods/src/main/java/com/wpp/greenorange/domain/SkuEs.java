package com.wpp.greenorange.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 存入到elasticsearch的实体类
 * @author 吴鹏鹏ppp
 */
public class SkuEs implements Serializable {
    private static final long serialVersionUID = -38907177693890253L;
    /**
     * id
     */
    private String id;
    /**
     * 价格
     */
    private Double price;
    /**
     * 商品名
     */
    private String name;
    /**
     * 一张的展示图片
     */
    private String oneShowImg;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 参数
     */
    Object params;
    /**
     * 销量
     */
    private Integer saleCount;
    /**
     * 评论数
     */
    private Integer evaluateCount;
    /**
     * 卖点
     */
    private String sellPoint;

    @Override
    public String toString() {
        return "SkuEs{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", showImg='" + oneShowImg + '\'' +
                ", createTime=" + createTime +
                ", goodsId=" + goodsId +
                ", categoryName='" + categoryName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", params=" + params +
                ", saleCount=" + saleCount +
                ", evaluateCount=" + evaluateCount +
                '}';
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public SkuEs() {
    }

    public String getOneShowImg() {
        return oneShowImg;
    }

    public void setOneShowImg(String oneShowImg) {
        this.oneShowImg = oneShowImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(Integer evaluateCount) {
        this.evaluateCount = evaluateCount;
    }
}
