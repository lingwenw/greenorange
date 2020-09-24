$.fn.extend({

})



$.extend({

    /**
     * 判断一个ajax请求是否出错，用layer进行提升
     * 判断一个ajax请求是否出错，用layer进行提示
     * @param resp 服务器响应的数据
     * @param fun 服务器正常返回的回调
     */
    errorAjax : function(resp,fun){
        if (resp.error){
            layer.alert(resp.message, {icon: 5,time:1000});
            layer.alert(resp.message, {icon: 5});
        }else{
            fun();
        }
    },

    //判断字符串是否为空
    emptyString : function (value) {
        if (typeof value == "string" && value.length==undefined) {
            return true;
        }
        //正则表达式用于判斷字符串是否全部由空格或换行符组成
        //返回值为true表示是空字符串
        return (value == null || value == undefined || !/\S/.test(value) )
    },
    /**
     * 以json格式获取url上的参数
     */
    getParameterJson : function(){
        var parameter = location.search.substr(1).split("&");
        var json = {};
        for (var i = 0; i < parameter.length; i++) {
            var temp = decodeURI(parameter[i]).split("=");
            json[temp[0]] = temp[1];
        }
        return json;
    },

    /**
     * 格式化日期
     * 参数1: 日期对象
     * 参数2: 格式(可以没有，没有时默认格式 yyyy-MM-dd HH:mm:ss )
     * @returns {string}
     */
    dateFormat : function(){

        function completion (str) {
            if ( str.length<2 ){
                str = 0+str;
            }
            return str;
        }


        var reg = arguments[1];
        if ( ! (arguments[0] instanceof Date) ){
            throw new Error("Date对象请放在第一个参数");
        }
        if ( reg == undefined ){
            reg = "yyyy-MM-dd HH:mm:ss";
        }

        var dt = arguments[0];
        var year = dt.getFullYear();
        var month = completion (dt.getMonth()+1+"") ;
        var day = completion (dt.getDate()+"") ;
        var hours = completion (dt.getHours()+"");
        var minutes = completion (dt.getMinutes()+"");
        var seconds =  completion (dt.getSeconds()+"");
        reg = reg.replace("yyyy",year).replace(/M{1,2}/,month).replace(/d{1,2}/,day)
            .replace(/H{1,2}/,hours).replace(/m{1,2}/,minutes).replace(/s{1,2}/,seconds);



        return reg;

    },

    /**
     * 把字符串转为数字
     * @param str
     * @returns {number}
     */
    parseFloat : function(str){
        return parseFloat( str.substring( str.search(/[\d]+/) ) );
    },

    /**
     * 获得一个随机数
     * 一个参数时获得 0~参数 的随机数
     * 两个参数获取 参数1~参数2 的随机数
     * @returns {number}
     */
    getRandom : function(){
        if(arguments.length == 1) return Math.floor( Math.random()*(arguments[0]+1) );
        return Math.floor( Math.random()*(arguments[1]-arguments[0]+1)+arguments[0] );
    }
})