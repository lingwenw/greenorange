$(function () {
    $(".address").hover(function () {
        $(this).addClass("address-hover");
    }, function () {
        $(this).removeClass("address-hover");
    });

})

$(function () {
    $(".addr-item .name").click(function () {
        $(this).toggleClass("selected").siblings().removeClass("selected");
    });
    $(".payType li").click(function () {
        $(this).toggleClass("selected").siblings().removeClass("selected");

    });
    //地址获取
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }
    // 调用方法
    var ids=GetQueryString("ids");
    var nums=GetQueryString("nums");
    var cartIds;
    var cartNums;
    var addressId;
    var goodsSkuId=new Array();
    var orderData={};
    //页面加载时，查询数据添加到页面
    $(document).ready(function(){
        $.ajax({ url:"/address/getConditions", success: function(data){
                inits(data);
            }});
        //商品订单
        cartIds=ids.split(":");
        cartNums=nums.split(":");
        $.ajax({ url:"/goodsFavourite/findAllByConditions",type: "post",data:{"ids":ids,"nums":nums}, success: function(data){
                $(".send-detail").empty();
                exhibit(data);
            }});
    });
    //从页面地址中获取商品id!
    //点击新增地址按钮时产生的页面样式
    $(".newadd").click(function () {
        $(".modal-title").text("新增地址");
        $(".sui-form").attr("action","/address/SetAddress");
        $(".input_name").val("");
        $(".placeholder").val("");
        $(".input-large").val("");
        $(".input-telephone").val("");
        $(".input-remarks").attr("name","notes");
        $(".input-remarks").val("");
        //
        $(".sui-modal").attr("style","display:block !important;");
        $(".sui-modal").show();
        //
        $(".control-development").hide();
    });
    //判断电话号码是否符合规格
    $(".input-telephone").blur(function (){
        var telephone=/^\d{11,}$/;
        var telephones=$(this).val();
        if (!telephone.test(telephones)){
            alert("电话号码不对");
            $(this).val("");
        }

    });
    //完成后的判断与传输
    $(".btn-primary").click(function () {
        //用户名获取用户id
        // var userName=null;
        // var userID;
        // $.ajax({ url:"/address/findByuUserId",data:{"name":userName}, success: function(data){
        // 		userID=data;
        // 	}});
        //获取新增数据input-medium
        var titles=$(".modal-title").text();
        var names=$(".input_name").val();
        var diZi1=$("#city-picker3").val();
        var diZi2=$(".input-large").val();
        var diZi=diZi1+"/"+diZi2;
        var dianHua=$(".input-telephone").val();
        var beiZu=$(".input-remarks").val();
        //判断数据是否为空
        if (names==null||diZi==null||dianHua==null){
            alert("请将内容填完才可完成！")
        } else {
            //判断是否是添加还是修改
            if (titles=="新增地址"){
                $.ajax({ url:"/address/SetAddress",data:{"contact":names,"address_1":diZi,"mobile":dianHua,"notes":null}, success: function(data){
                        if (data){
                            $(".sui-close").click();
                            $.ajax({ url:"/address/getConditions", success: function(data){
                                    inits(data);
                                }});
                        }
                    }});
            } else if (titles=="修改地址"){
                $.ajax({ url:"/address/update",data:{"contact":names,"address_1":diZi,"mobile":dianHua,"id":beiZu}, success: function(data){
                        if (data){
                            $(".sui-close").click();
                            $.ajax({ url:"/address/getConditions", success: function(data){
                                    inits(data);
                                }});
                        }
                    }});
            }
        }
    });
    //页面样式设置
    function inits(data) {
        var tbody=$(".addr-item");
        tbody.empty();
        var obj = eval(data);
        $(obj).each(function (index) {
            var val = obj[index];
            tbody.append("<div data-title="+val.id+">" +
                "<div class='con name' name='contact' data-title="+val.userId+"><a data-title="+val.contact+" href='javascript:;'>"+val.contact+"<span title='点击取消选择'>&nbsp;</a></div>"+
                "<div class='con address' name='address' data-title="+val.address+">"+val.address+"<span name='mobile'>"+val.mobile+"</span>"+
                "<span class='edittext'><a class='edit-modify XiuGai_1' data-toggle='modal' data-target='.edit' data-keyboard='false' >编辑</a>&nbsp;&nbsp;<a class='edit-modify ShanChu_1' href='javascript:;'>删除</a></span>" +
                "</div>" +
                "<div class='clearfix'></div>");
            //点击修改时，进行判断修改
            $(".XiuGai_1").click(function () {
                //
                $(".modal-title").text("修改地址");
                $(".sui-form").attr("action","/address/update");
                var id=$($(this).parent().parent().parent()).attr("data-title");
                var contact=$($(this).parent().parent().prev().find("a")).attr("data-title");
                var address=$($(this).parent().parent()).text();
                var address_s=address.split(" ");
                var address_1=address_s[0].toString()+"/"+address_s[1].toString()+"/"+address_s[2].toString();
                var address_2=address_s[3].toString();
                var mobile=$($(this).parent().prev()).text();
                $(".input_name").val(contact);
                $(".placeholder").val(address_1);
                $(".input-large").val(address_2);
                $(".input-telephone").val(mobile);
                $(".input-remarks").attr("name","id");
                $(".input-remarks").val(id);
                $(".sui-modal").css("display","block");
                $(".control-development").hide();
                //
                $.ajax({ url:"/address/getConditions", success: function(data){
                        inits(data);
                    }});
            });
            //点击删除时，进行数据删除操作！
            $(".ShanChu_1").click(function () {
                var id=$($(this).parent().parent().parent()).attr("data-title");
                $.ajax({ url:"/address/deleteById",data :{"id":id}, success: function(data){
                        if (data){
                            $.ajax({ url:"/address/getConditions", success: function(data){
                                    inits(data);
                                }});
                        }
                    }});
            });
            //触屏显现
            $(".addr-item div").mousemove(function () {
                $(this).find(".edittext").css("visibility","visible");
            });
            $(".addr-item div").mouseleave(function () {
                $(this).find(".edittext").css("visibility","hidden");
            });
            $(".name").click(function () {
                $(".name").removeClass("selected");
                $(this).addClass("selected");
                addressId=$(this).parent().attr("data-title");
                $.ajax({ url:"/address/getOne",data:{"id":addressId}, success: function(data){
                        $(".trade .fc-receiverInfo").text("寄送至:"+data.address+" 收货人:"+data.mobile+" "+data.contact);
                    }});

            });
        });
    }
    // $(".submit").click(function () {
    //
    // })
    //根据用户id从数据库中获取商品订单
    function exhibit(data) {
        var tbody=$(".send-detail");
        for (var i = 0; i <data.length ; i++) {
            goodsSkuId.push(data[i].goodsSku.id);
            var showimg=data[i].goodsSku.showImg.split(",");
            var showimg_1=showimg[0].split("[");
            //
            //
            var param=data[i].goodsSku.params.split(";");
            var param_1=param[0].split(",");
            tbody.append("<li><div class='sendGoods'>"+
                "<ul class='yui3-g'>"+
                "<li class='yui3-u-1-6'>"+
                "<span><img src=../img/goods_img/"+showimg_1[1].toString()+"></span>"+
                "</li>"+
                "<li class='yui3-u-7-12'>"+
                "<div class='desc'>"+data[i].goodsSku.title+"</div>"+
                "<div class='seven'>7天无理由退货</div></li>"+
                "<li class='yui3-u-1-12'>"+
                "<div class='price' data-number='"+data[i].goodsSku.price+"'>￥"+data[i].goodsSku.price+".00</div></li>"+
                "<li class='yui3-u-1-12'>"+
                "<div class='num'>X1</div></li>"+
                "<li class='yui3-u-1-12'>"+
                "<div class='exit'>有货</div>"+
                "</li></ul></div></li>");
            var num=$(tbody).find(".price");
            nums=nums+parseFloat(num.attr("data-number"));
        };
        //金钱
        var nums=$(".num");
        var price=$(".price");
        var shu=0;
        var money=0;
        for (var i = 0; i <nums.length ; i++) {
            $(nums.get(i)).text("×"+cartNums[i]);
            shu+=parseInt(cartNums[i]);
            money+=parseInt(cartNums[i])*parseInt($(price.get(i)).attr("data-number"));
        }
        $(".list .number").text(shu);
        $(".list .allprice").text("￥"+money);
        $(".trade .price").text("￥"+money);
        // alert(cartNums+":"+goodsSkuId);
        //传值cartNums数量集合，goodsSkuId集合
        orderData.skus = [];
        for (var i = 0; i <goodsSkuId.length ; i++) {
            var temp = {
                id:parseInt(goodsSkuId[i]),
                count:parseInt(cartNums[i])
            }
            orderData.skus .push(temp)
        }
    }
    //
    $(".submit a").click(function () {
        orderData.addressId=parseInt(addressId);
        if (addressId==null){
            alert("请选择地址！");
        }else {
            axios.post("order/addOrder", orderData)
                .then(function (resp) {
                    // console.log(!resp.data.error)
                    if (resp.data != null && (!resp.data.error)) {
                        //商户订单号，商户网站订单系统中唯一订单号，必填
                        //付款金额，必填
                        //订单名称，必填
                        window.open("pay/pagePay/" + resp.data.id + "/" + resp.data.price + "/" + resp.data.subject)
                    } else {
                        layer.alert('服务器繁忙，请稍后再试', {icon: 5});
                    }
                })
                .catch(function (resp) {
                    console.log("服务器繁忙")
                })
        }
        //创建订单
        // var orderData = {
        //     "skus": [{
        //         "id": 10000,
        //         "count": 2
        //     }, {
        //        "id": 10003,
        //         "count": 2
        //     }
        //     ],
        //     "addressId": 77
        // }

    })
})
