package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (Advert)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:28:53
 */
public class Advert implements Serializable {
    private static final long serialVersionUID = 578771010034713549L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 图片路径
     */
    private String imgUrl;
    /**
     * 广告位置id
     */
    private Integer positionId;
    /**
     * 简介，描述
     */
    private String describe;
    /**
     * 点击广告跳转的链接
     */
    private String href;
    /**
     * 广告排序，越大越靠后
     */
    private Integer index;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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