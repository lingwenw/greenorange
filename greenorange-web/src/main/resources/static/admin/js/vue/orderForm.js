var orderVue = new Vue({
    el : "#table_order_list",
    data : {
        pageInfo : {}
    },
    methods : {
        //获得分页数据
        getAllLimit : function (pageNum) {
            let params = {
                pageNum : pageNum,
                pageSize : $("#sample-table_length select>option:selected").val(),
                id : $("#orderNum").val(),
                startTimeStr : $("#startDate").val(),
                endTimeStr : $("#endDate").val(),
                userId : $("#userId").val(),
                statusId : $("#order_status").data("statusId")
            }
            axios.get("../order/getAllLimit",{
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
        //初始化数据
        initItemData : function (item) {
            console.log(111)
            let orderGoods = item.orderGoods;
            if (orderGoods==null||orderGoods.length==0){
                return [];
            }
            //展示的img，url
            let res = [];
            //总数
            let count = 0;
            //遍历订单商品
            orderGoods.forEach((e,i) => {
                let sku = e.sku;
                if (sku!=null){
                    let temp = {};
                    temp.id = sku.id;
                    if (!$.emptyString(sku.showImg) && i<3){
                        temp.img = JSON.parse(sku.showImg)[0];
                    }
                    temp.title = sku.title;
                    res.push(temp)
                }
                count += e.count;
            })

            item.res = res;
            item.count = count;
        }
    },
    created : function () {
        this.getAllLimit(1);
    }
})