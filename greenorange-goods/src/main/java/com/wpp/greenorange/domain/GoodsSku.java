package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (GoodsSku)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:29:30
 */
public class GoodsSku implements Serializable {
    private static final long serialVersionUID = 730701029185268015L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 颜色id
     */
    private Integer colorId;
    /**
     * 颜色代码，json格式
     */
    private String color;
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

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

}