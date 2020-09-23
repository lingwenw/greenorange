package com.wpp.greenorange.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * (Admin)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:28:42
 */
public class Admin implements Serializable, UserDetails {
    private static final long serialVersionUID = -21023189685204466L;

    private String password;

    private List<GrantedAuthority> authorities;

    private List<Map<String, Object>> powers;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 账号
     */
    private String accountnumber;
    /**
     * 密码
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
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

    public List<Map<String, Object>> getPowers() {
        return powers;
    }

    public void setPowers(List<Map<String, Object>> powers) {
        this.powers = powers;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) &&
                Objects.equals(accountnumber, admin.accountnumber) &&
                Objects.equals(password, admin.password);
    }

    public Admin() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Admin{" +
                "id=" + id +
                ", accountnumber='" + accountnumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}