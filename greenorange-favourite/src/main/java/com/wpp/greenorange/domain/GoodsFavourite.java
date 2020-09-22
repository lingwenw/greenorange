package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * (GoodsFavourite)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:29:09
 */
public class GoodsFavourite implements Serializable {
    private static final long serialVersionUID = -38368732605157584L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * sku id
     */
    private Integer skuId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 状态id
     */
    private Integer statusId;
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
     * 对象
     */
    private GoodsSku goodsSku;

    public GoodsSku getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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


    @Override
    public String toString() {
        return "GoodsFavourite{" +
                "id=" + id +
                ", skuId=" + skuId +
                ", userId=" + userId +
                ", statusId=" + statusId +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", goodsSku=" + goodsSku +
                '}';
    }
}