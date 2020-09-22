package com.wpp.greenorange.domain.select;

import com.wpp.greenorange.domain.Goods;
import com.wpp.greenorange.domain.GoodsSku;

/**
 * GoodsSku的辅助查询类
 * @author 吴鹏鹏ppp
 */
public class GoodsSkuSelect extends GoodsSku {
    private Integer pageSize;
    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
