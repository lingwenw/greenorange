var skuListVue = new Vue({
    el : "#testIframe",
    data : {
        pageInfo : {}
    },
    methods : {
        //更新价格和库存
        updatePriceStock: function(sku){
            let _this = this;
            axios.put("../sku/updatePriceStock",sku)
            .then( resp => {
                let data = resp.data;
                $.errorAjax(data,function () {
                    layer.msg("更新成功",{icon:1,time: 1500})
                    _this.getAllLimit(_this.pageInfo.pageNum);
                })
            } )
        },
        //删除一个sku
        deleteSku : function(id, goodsId){
            let _this = this;
            layer.confirm("确定要删除sku吗?",{
                btn: ['确定', '取消'],
                icon: 7
            }, function (index) {
                layer.close(index)
                //发送请求删除商品
                let params = new URLSearchParams();
                params.append("id",id);
                params.append("goodsId",goodsId);

                axios.delete("../sku/deleteSku",{data:params})
                .then( resp =>{
                    let data = resp.data;
                    $.errorAjax(data,function () {
                        layer.msg("删除成功",{icon:1,time: 1500})
                        _this.getAllLimit(_this.pageInfo.pageNum);
                    })
                } )
            });
        },
        //获得分页信息的方法
        getAllLimit : function (pageNum) {
            let _this = this;
            axios.get("../sku/getAllLimit", {
                params: {
                    pageNum: pageNum,
                    pageSize: $("#sample-table_length select>option:selected").val(),
                    goodsId : $("#input-goodsId").val()
                }
            })
            .then(resp => {
                _this.pageInfo = resp.data;
            })
        },
        //启用禁用sku
        enableSku : function (id, goodsId,deleted) {
            let _this = this;
            axios.put("../sku/enableSku",{
                id : id,
                goodsId : goodsId,
                deleted : deleted
            })
            .then( resp => {
                let data = resp.data;
                $.errorAjax(data,function () {
                    _this.getAllLimit( _this.pageInfo.pageNum );
                    layer.msg("操作成功",{icon: 1,time:1000})
                })
            } )
        },
        /*产品-编辑*/
        member_edit : function (title, url, w, h) {
            layer_show(title, url, w, h);
        },
        replaceEdition : function (edition) {
            let jsonEdition = JSON.parse(edition);
            let str = "";
            for (let key in jsonEdition) {
                str += jsonEdition[key]+"<br />";
            }

            return str;
        }
    },
    created: function () {
        this.getAllLimit(1);
    }
})