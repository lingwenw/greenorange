var orderVue = new Vue({
    el : ".order-detail",
    data : {
        pageInfo : {}
    },
    methods : {
        //获得分页数据
        getAllLimit : function (pageNum) {
            let params = {
                pageNum : pageNum,
                statusId : statusId,
                pageSize : 1
            }
            axios.get("../order/getUserOrderLimit",{
                params : params
            })
                .then( resp => {
                    let data = resp.data;
                    let _this = this;
                    $.errorAjax(data,function () {
                        _this.pageInfo = data;
                    })
                } )
        },
        getOriginalPrice : function(orderGoods){
            return orderGoods.originalPrice*orderGoods.count;
        },
        getPaidPrice : function(orderGoods){
            return orderGoods.paidPrice*orderGoods.count;
        },
        //初始化数据
        initItemData : function (orderGoods) {

            let edition = "";
            let editionMap = this.parseMap(orderGoods.sku.skuEdition);
            for ( let key in editionMap ){
                edition += editionMap[key]+",";
            }
            orderGoods.edition = edition.substring(0,edition.lastIndexOf(","));
        }
    },
    created : function () {
        this.getAllLimit(1);
    }
})