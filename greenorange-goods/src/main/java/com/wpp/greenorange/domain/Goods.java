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
     * 分类名称
     */
    private String categoryName;
    /**
     * 商品名
     */
    private String name;
    /**
     * 品牌id
     */
    private Integer brandId;
    /**
     * 品牌名
     */
    private String brandName;
    /**
     * 卖点
     */
    private String sellPoint;
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
     * 可选择的版本信息
     */
    private String editionChoice;


    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", name='" + name + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", editionChoice='" + editionChoice + '\'' +
                '}';
    }

    public String getEditionChoice() {
        return editionChoice;
    }

    public void setEditionChoice(String editionChoice) {
        this.editionChoice = editionChoice;
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
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