package com.wpp.greenorange.pay;
import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117651113";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDB0fmqHTSgrXjyqsrw96Wagnzzp27vJpzph+PXqZCUv/O8RGa2+4gZavNzB0SmromTXR8fkpA+SZWXASjp3BdCiQev0UEUCodloCWs+dq3UX6to+5ZmBtBldGdkIlgHzLLAAnDQdLqUdLKSl0sAkbQxOhl2h73HvqaXqbMUIy9JjEyfEqZx4u4MemdJG09EXMZW7DkwMD4JA6RSeEcDgd+NTm1eWtG1lVqrb7PPTFkDX2r/gaFLnvij8lARhvtrkByATmbFXNyZs0viHWsMfcUcQ2jnYmOXGw5FZX4+VpIX3k6AI8V3BxpCus4zHa9gvfcyV0LOSoSxtK0062PXuXjAgMBAAECggEBAIWhgNJB+qQBlo8xJGTJSdtitkXDnd+zLqJwd62ckjuMsL6QJoVonOhxbdJUI4gMdHwuyyOYiGz5jPKk8RF0FnFgSvV0f7sSm1F8/hzp01trC70qdk49aiVo5zIMkjq8p+eAajq+am315jBk3yJVm3+bE2QN5CWbrUq7kaLysmZB+Gt1ZINa0DO4nY9yJTOR/pncIlvzupBbrfKtjHJHJyDfCADyQPrihw/0s6ql3wyyydHIYXz6oG/usPxuvqGl6sYbOFBrIDfPMsZ1z2p/bEz48XZB1fbnC/inMTwpRuVywvY8ybXlm87zAkuhdgsWEKfIeKwrucKBdqLPBSkEHMkCgYEA55FgUyWht6t3UQy8q6MjgHlnbTc5qtu8y59V/vuQ0pMkPG3Co6g9nHMr/ulPHQOCSWL8iziQJbD4POPilBvRDBXchMq/bt8xvLOILxgACBtLJJy891xVYo2qn695jrkEnB3zJXrdyPbgZELHqdGpSSX5iYV0fh6X3gGxLKtNvn8CgYEA1kUJ56R3wV91fyo0jAMTgQfJ/234c7keOXy6iRIECMcFW84ZuWMCNHUEpN8MhcbHxyJcD8lxOY/yNDb/VBROnbYqAcWL1O7yq+7spSW0myA2A90Y8UhZkbmWKD6M5Mjm//s4v7xShilGCba1A4MKtVaKkUJ+ITag1tcrgCjp7p0CgYBHzjqSTd8Btze+eDag+mtt0TeTFEbbhlvawEPIV3Wm9l2MJ3TtjqPcOZJw7yPLGWVzIDCzcwyvw1qWdwuxZZbDKpxAySXnWWpw2zLld4m6+cupRLkL8yO+9bLs5VrKOPEG//p/qsPPJ06ZiGOlcxANUKs6S5m96fqpqtinw07OPQKBgDPxEoCb5wfyaxpDke9rA3xg9f+8meD7vC71ZIa73kx+anch1CyTqsIqp9CL3Y5EbMOCOcz96TuqAG3V8zlfntsugKpjSA17S0qQpb83D7wXJ+Dx5Q0Am+3ujk79Msx3Q0BElzIrpdmuAbNzHhhW2O6ypu6lrsvAYWcTTBFKWcMlAoGBALVZnaKWZWtuY1ofhHTvOOhuROC9ZM3hG1U7gZaUXtVSLvE8S+MD801g2LZt7JaDLECjbqRyk5koil4cILmWqphLSVUgaTcgxKbvdbKuTg1tomWMJSfsAh3yP6R8+2Ofp8h06oyFZnULzMXwojad42LJp8xQR459nxqr3cswJ/hi";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs9v4DmXMzy2lGugmtUyAH6XUVB/GgzXJKcpbz27LVDVXXU4wMdakAMuM7uQZ45DtLrZpfW6ZQBtAWSEVZB1ExYtJw2IQB3VxveIQ2qoVgUl56P8r94ymDPSq4aoxeQTrcy13mBeHpuLMl0u4nNCcMKEVEXSIEtQlMAVBpXrKCrsVZHMI8z0XuPHRGiV4ItjXI2AtGhAV+E/14QDPzY/a5QyNauafHIC7uMrQ5PgLanhh3YWLqmEFQkxQfrYH1anDNUhxrYNq+5srkVRoVxuxQ141lZdYhxEFXkQlLzDrcMnRa9oyZc537bjiJObLeN3iPt1XRF9L5jRFW6OeHyIaLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://121.89.197.100:8080/pay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://121.89.197.100:8080/pay/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
//    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}


