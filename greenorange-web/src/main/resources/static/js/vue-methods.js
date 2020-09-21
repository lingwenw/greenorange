Vue.prototype.parseJson = function(str){
    if (!$.emptyString(str)){
        return JSON.parse(str);
    }else{
        return null;
    }
}
Vue.prototype.parseArr = function(str){
    if (!$.emptyString(str)){
        return JSON.parse(str);
    }else{
        return [];
    }
}
Vue.prototype.parseMap = function(str){
    if (!$.emptyString(str)){
        return JSON.parse(str);
    }else{
        return {};
    }
}
/**
 * 格式化日期
 * 参数1: 日期对象
 * 参数2: 格式(可以没有，没有时默认格式 yyyy-MM-dd HH:mm:ss )
 * @returns {string}
 */
Vue.prototype.dateFormat = function(){

    function completion (str) {
        if ( str.length<2 ){
            str = 0+str;
        }
        return str;
    }


    let reg = arguments[1];
    if ( ! (arguments[0] instanceof Date) ){
        throw new Error("Date对象请放在第一个参数");
    }
    if ( reg == undefined ){
        reg = "yyyy-MM-dd HH:mm:ss";
    }
    let dt = arguments[0];
    let year = dt.getFullYear();
    let month = completion (dt.getMonth()+1+"") ;
    let day = completion (dt.getDate()+"") ;
    let hours = completion (dt.getHours()+"");
    let minutes = completion (dt.getMinutes()+"");
    let seconds =  completion (dt.getSeconds()+"");
    reg = reg.replace("yyyy",year).replace(/M{1,2}/,month).replace(/d{1,2}/,day)
        .replace(/H{1,2}/,hours).replace(/m{1,2}/,minutes).replace(/s{1,2}/,seconds);



    return reg;

}

