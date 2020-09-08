package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品类目(Category)实体类
 *
 * @author 吴鹏鹏ppp
 * @since 2020-09-04 02:29:02
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 258779950551000042L;
    /**
     * 类目ID
     */
    private Integer id;
    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    private Integer parentId;
    /**
     * 类目名称
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

    /**
     *
     * 级联子目录
     */
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", categories=" + categories +
                '}';
    }
}