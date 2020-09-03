package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (Address)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:28:11
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 395261555728639149L;

    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 收件人
     */
    private String contact;
    /**
     * 是否是默认 1默认 0否
     */
    private Boolean isDefault;
    /**
     * 备注
     */
    private String notes;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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