var skuAddVue = new Vue({
    el : "#form-article-add",
    data : {
        params : {},
        goods : {},
        sku : {},
        showImg : []
    },
    methods : {
        //修改sku
        updateSku : function(){
            let _this = this;
            //展示图片
            if (_this.showImg.length>0){
                _this.sku.showImg = JSON.stringify(_this.showImg)
            }
            _this.sku.params = JSON.stringify(_this.params);
            _this.sku.skuEdition = JSON.stringify(_this.sku.skuEdition);
            _this.sku.introduceData = editor.txt.html();
            console.log(_this.sku)
            // axios.put("../sku/updateSku",_this.sku)
            // .then( resp => {
            //     let data = resp.data;
            //     $.errorAjax(data,()=>{
            //         setTimeout(function () {
            //             try {
            //                 let goodsVue = parent.goodsVue;
            //                 goodsVue.getAllLimit(goodsVue.pageInfo.pageNum);
            //                 layer_close();
            //             } catch (e) {
            //
            //             }
            //             location.reload();
            //         },1000)
            //     })
            // } )
        },
        /**
         * 获得页面展示数据的方法
         */
        getInfoSkuAddNeed : function () {
            let _this = this;
            let params = $.getParameterJson();
            axios.get("../sku/getSkuInfo",{
                params : params
            })
            .then( resp =>{
                let data = resp.data;
                //效验paramType
                if ( !$.emptyString(data.sku.params) ) {
                    _this.params = JSON.parse(data.sku.params);
                }else{
                    _this.params = {};
                }
                _this.goods = data.goods;
                _this.sku = data.sku;
                //效验skuEdition
                let skuEdition =  _this.sku.skuEdition;
                if ( !$.emptyString(skuEdition) ){
                    _this.sku.skuEdition = JSON.parse(skuEdition);
                }else{
                    _this.sku.skuEdition = {};
                }
                //创建富文本框
                editor.create();
                editor.txt.html(_this.sku.introduceData);
            } )
        }
    },
    created: function () {
        this.getInfoSkuAddNeed();
    }

})