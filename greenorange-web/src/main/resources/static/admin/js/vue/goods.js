//vue请求数据
var goodsVue = new Vue({
    el: "#products_style",
    data: {
        pageInfo: {}
    },
    methods: {
        //删除商品的方法
        deleteGoods : function(id){
            let _this = this;
            layer.confirm("数据无价，推荐停用商品。点击确定后该商品及其sku都将删除!",{
                btn: ['确定', '取消'],
                icon: 7
            }, function (index) {
                layer.close(index)
                //发送请求删除商品
                let params = new URLSearchParams();
                params.append("id",id);
                axios.delete("../goods/deleteGoods",{data:params})
                .then( resp => {
                    let data = resp.data;
                    $.errorAjax(data,function () {
                        if (data){
                            _this.getAllLimit(_this.pageInfo.pageNum);
                            layer.msg("删除商品成功!",{icon:1})
                        }
                    })
                } )
            });
        },
        //获得分页信息的方法
        getAllLimit : function (pageNum) {
            let _this = this;
            axios.get("../goods/getAllLimit", {
                params: {
                    pageNum: pageNum,
                    pageSize: $("#sample-table_length select>option:selected").val(),
                    brandName : $(".text_add").val(),
                    categoryId : $("#scrollsidebar").data("categoryId"),
                    categoryName : $(".text_brand").val(),
                    name : $(".text_name").val()
                }
            })
            .then(resp => {
                _this.pageInfo = resp.data;

            })
        },
        //启用或停用的方法
        enable: function (id,deleted) {
            let _this = this;
            axios.put("../goods/enableGoods",{
                id : id,
                deleted : deleted
            })
            .then(resp => {
                let data = resp.data;
                $.errorAjax(data,function () {
                    if (data.success) {
                        layer.msg(data.message, {icon: 1,time:2000})
                        _this.getAllLimit(_this.pageInfo.pageNum);
                    }else{
                        layer.alert(data.message, {icon: 5,time:2000})
                    }
                })
            })
        },
        /*产品-编辑*/
        member_edit : function (title, url, w, h) {
            layer_show(title, url, w, h);
        },
    },
    created: function () {
        this.getAllLimit(1);
    }
})