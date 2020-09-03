package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (Goods)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:29:29
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 615905797717511924L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 标题
     */
    private String title;
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
     * 好评数
     */
    private Integer goodEvaluate;
    /**
     * 差评数
     */
    private Integer badEvaluate;
    /**
     * 尺寸，大小
     */
    private String size;
    /**
     * 最低价
     */
    private Float minprice;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getMinprice() {
        return minprice;
    }

    public void setMinprice(Float minprice) {
        this.minprice = minprice;
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