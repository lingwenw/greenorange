package com.wpp.greenorange.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpp.greenorange.domain.Order;
import com.wpp.greenorange.domain.OrderGoods;
import com.wpp.greenorange.pay.AlipayConfig;
import com.wpp.greenorange.service.OrderGoodsService;
import com.wpp.greenorange.service.OrderService;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderGoodsService orderGoodsService;

    @RequestMapping(value = "/pagePay/{orderNumber}/{price}/{orderName}", produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String pagePay(HttpServletRequest req,
                   @PathVariable("orderNumber") String orderNumber,
                   @PathVariable("price") String price,
                   @PathVariable("orderName") String orderName
    ) throws AlipayApiException, JsonProcessingException {


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
//        String orderNumber ;
        //付款金额，必填
//        String price ;
        //订单名称，必填
//        String orderName ;
        //商品描述，可空
        String body = null;
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderNumber + "\","
                + "\"total_amount\":\"" + price + "\","
                + "\"subject\":\"" + orderName + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //输出

        return result;
    }

    @RequestMapping(value = "/notify", produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String notify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序


                Order order = new Order();
                order.setId(Integer.parseInt(out_trade_no));
                order.setStatusId(2);
                order.setTradeNo(trade_no);
                order.setPaymentTime(new Date());

                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setStatusId(2);
                orderGoods.setOrderId(Integer.parseInt(out_trade_no));

                orderService.update(order);
                orderGoodsService.update(orderGoods);
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //支付成功，改变数据库
                Order order = new Order();
                order.setId(Integer.parseInt(out_trade_no));
                order.setStatusId(2);
                order.setTradeNo(trade_no);
                order.setPaymentTime(new Date());

                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setStatusId(2);
                orderGoods.setOrderId(Integer.parseInt(out_trade_no));

                orderService.update(order);
                orderGoodsService.update(orderGoods);
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            return "success";

        } else {//验证失败
            return "fail";

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    @RequestMapping(value = "/returnUrl", produces = "text/html;charset=UTF-8")
    public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException, ServletException {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        //付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //判断该笔订单是否在商户网站中已经做过处理
        //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
        //如果有做过处理，不执行商户的业务程序


        Order order = new Order();
        order.setId(Integer.parseInt(out_trade_no));
        order.setStatusId(2);
        order.setTradeNo(trade_no);
        order.setPaymentTime(new Date());

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setStatusId(2);
        orderGoods.setOrderId(Integer.parseInt(out_trade_no));

        orderService.update(order);
        orderGoodsService.update(orderGoods);
        //注意：
        //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
        //——请在这里编写您的程序（以上代码仅作参考）——
        ModelAndView view = new ModelAndView();
        view.addObject("total_amount",total_amount);
        view.setViewName("paysuccess");
        return view;
    }
}
