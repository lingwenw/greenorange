package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (OrderGoods)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:29:47
 */
public class OrderGoods implements Serializable {
    private static final long serialVersionUID = 324683460591416231L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * sku id
     */
    private Integer skuId;
    /**
     * 原价
     */
    private Double originalPrice;
    /**
     * 实付价
     */
    private Double paidPrice;
    /**
     * 订单号
     */
    private Integer orderId;
    /**
     * 状态id
     */
    private Integer statusId;
    /**
     * 数量
     */
    private Integer count;
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
     * sku
     */
    private GoodsSku sku;
    /**
     * 状态
     */
    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public GoodsSku getSku() {
        return sku;
    }

    public void setSku(GoodsSku sku) {
        this.sku = sku;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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