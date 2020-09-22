package com.wpp.greenorange.domain.select;

import com.wpp.greenorange.domain.Goods;
/**
 * goods的辅助查询类
 * @author 吴鹏鹏ppp
 */
public class GoodsSelect extends Goods {
    private Integer pageSize;
    private Integer pageNum;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {

        return super.toString()+"GoodsSelect{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
