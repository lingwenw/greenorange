package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Order)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:29:46
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -45935669689818529L;

    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 地址id
     */
    private Integer addressId;
    /**
     * 状态id
     */
    private Integer statusId;
    /**
     * 总价
     */
    private Double price;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 付款时间
     */
    private Date paymentTime;
    /**
     * 确认收货时间
     */
    private Date receivingTime;
    /**
     * 订单名称
     */
    private String subject;
    /**
     * 支付宝交易号
     */
    private String tradeNo;
    /**
     * 是否删除（逻辑删除）
     */
    private Boolean deleted;
    /**
     * 最近一次修改时间
     */
    private Date updateTime;
    /**
     * 地址
     */
    private Address address;
    /**
     * 状态名
     */
    private String statusName;
    /**
     * 订单商品
     */
    private List<OrderGoods> orderGoods;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}