package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (GoodsSku)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-08 22:11:32
 */
public class GoodsSku implements Serializable {
    private static final long serialVersionUID = -47793556165979269L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 标题
     */
    private String title;
    /**
     * sku参数
     */
    private String skuEdition;
    /**
     * 价格
     */
    private Double price;
    /**
     * 库存
     */
    private Integer stock;
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
     * 展示图片
     */
    private String showImg;
    /**
     * 销量
     */
    private Integer saleCount;
    /**
     * 好评数
     */
    private Integer goodEvaluate;
    /**
     * 差评数
     */
    private Integer badEvaluate;
    /**
     * 商品参数
     */
    private String params;
    /**
     * 介绍图片，资源
     */
    private String introduceData;

    public String getIntroduceData() {
        return introduceData;
    }

    public void setIntroduceData(String introduceData) {
        this.introduceData = introduceData;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "GoodsSku{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", skuEdition='" + skuEdition + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", showImg='" + showImg + '\'' +
                ", saleCount=" + saleCount +
                ", goodEvaluate=" + goodEvaluate +
                ", badEvaluate=" + badEvaluate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkuEdition() {
        return skuEdition;
    }

    public void setSkuEdition(String skuEdition) {
        this.skuEdition = skuEdition;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
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

}