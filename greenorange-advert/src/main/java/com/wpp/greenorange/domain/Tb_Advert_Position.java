package com.wpp.greenorange.domain;

import java.io.Serializable;
import java.util.Date;

public class Tb_Advert_Position implements Serializable {
    //主键id
    private  Integer ID;
    //位置描述
    private String position;
    //是否删除（逻辑删除）
    private boolean deleted;
    //创建时间
    private Date create_time;
    //最近一次修改时间
    private Date update_time;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
