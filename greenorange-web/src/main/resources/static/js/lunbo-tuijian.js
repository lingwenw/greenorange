new Vue({
    //页面元素
    el : "#ad-carousel",
    //声明数据
    data : {
        advertList : []
    },
    //声明方法
    methods : {
        getAllByPositionId : function () {
            var _this = this;
            //异步请求
            axios.get("advert/getAllByPositionId",{
                params : {
                    PositionId : 1
                }
            })
                //成功
                .then(function (resp) {
                    console.log(resp.data)
                    _this.advertList = resp.data;
                })
                .catch(function (resp) {
                    console.log("服务器繁忙")
                })
        }
    },
    //vue创建时
    created : function () {
        this.getAllByPositionId();
    }
})
// 推荐
new Vue({
    //页面元素
    el : "#b-Recommend",
    //声明数据
    data : {
        advertLists : []
    },
    //声明方法
    methods : {
        getAllByPositionIds : function () {
            var _this = this;
            //异步请求
            axios.get("advert/getAllByPositionId",{
                params : {
                    PositionId : 2
                }
            })
                //成功
                .then(function (resp) {
                    console.log(resp.data)
                    _this.advertLists = resp.data;
                })
                .catch(function (resp) {
                    console.log("服务器繁忙")
                })
        }
    },
    //vue创建时
    created : function () {
        this.getAllByPositionIds();
    }
})