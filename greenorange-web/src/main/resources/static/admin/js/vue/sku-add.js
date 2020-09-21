var skuAddVue = new Vue({
    el : "#form-article-add",
    data : {
        paramType : {},
        goods : {},
        sku : {},
        showImg : []
    },
    methods : {
        //新增一个sku
        addSku : function(){
            let _this = this;
            //展示图片
            _this.sku.showImg = JSON.stringify(_this.showImg)
            //版本选择
            let editionChoice = _this.goods.editionChoice;
            for (let key in editionChoice) {
                if ($.emptyString(editionChoice[key])){
                    layer.alert("请填写版本信息!",{icon:5});
                    return;
                }
            }
            _this.sku.skuEdition = JSON.stringify( editionChoice );
            //详细参数
            let paramType = _this.paramType;
            for (let key in paramType) {
                if (  $.emptyString(paramType[key]) || paramType[key]==true){
                    delete paramType[key];
                }
            }
            _this.sku.params = JSON.stringify(paramType)

            //富文本框
            _this.sku.introduceData = editor.txt.html();

            console.log(_this.sku)
            axios.put("../sku/addSku",_this.sku)
            .then( resp => {
                let data = resp.data;
                $.errorAjax(data,()=>{
                    setTimeout(function () {
                        let goodsVue = parent.goodsVue;
                        goodsVue.getAllLimit(goodsVue.pageInfo.pageNum);
                        layer_close();
                    },1000)
                })
            } )
        },
        /**
         * 获得页面展示数据的方法
         */
        getInfoSkuAddNeed : function () {
            let _this = this;
            axios.get("../goods/getInfoSkuAddNeed",{
                params : {
                    goodsId : $.getParameterJson().goodsId
                }
            })
            .then( resp =>{
                let data = resp.data;
                // console.log(data)
                _this.sku.goodsId = data.goods.id;
                //效验paramType
                if ( !$.emptyString(data.category.paramType) ) {
                    _this.paramType = JSON.parse(data.category.paramType);
                }
                _this.goods = data.goods;
                //效验editionChoice
                let editionChoice =  _this.goods.editionChoice;
                if ( !$.emptyString(editionChoice) ){
                    _this.goods.editionChoice = JSON.parse(editionChoice);
                }

            } )
        }
    },
    created: function () {
        this.getInfoSkuAddNeed();
    }

})