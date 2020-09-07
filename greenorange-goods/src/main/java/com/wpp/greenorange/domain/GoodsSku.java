package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (GoodsSku)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-06 12:17:40
 */
public class GoodsSku implements Serializable {
    private static final long serialVersionUID = -25101515350189499L;
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

}