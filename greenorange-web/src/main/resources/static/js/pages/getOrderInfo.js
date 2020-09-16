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


    $(".submit a").click(function () {
        //创建订单
        var orderData = {
            "skus": [{
                "id": 10000,
                "count": 2
            }, {
                "id": 10003,
                "count": 2
            }
            ],
            "addressId": 77
        }
        axios.post("order/addOrder", orderData)
            .then(function (resp) {
                console.log(!resp.data.error)
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
    })
})
