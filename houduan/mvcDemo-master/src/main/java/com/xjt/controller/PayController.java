package com.xjt.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.pay.AilpayRequest;
import com.xjt.entity.pay.AilpayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

@Controller
@RequestMapping("/api")
public class PayController {
    @RequestMapping("/ailpay")
    @ResponseBody
    @CrossOrigin
    public Result ailpay(@RequestBody Request<AilpayRequest> request) throws Exception{
        Result result = new Result();
        AilpayRequest ailpayRequest = request.getData();
        //去沙箱里面找自己的
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2021000117611617",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzyNUuBlz4A5vbZci39Nq4NgC1Yj4TSwzm5mmmrAZvfTjewF4N/wVmU9jxvmACkBv5kHGGij0bRGuPBBcSW1Zv2EOvimfSU8cUDNN8XDmCtEz7ib08G0UEWJO5VAn+aF+/Ff430Tw9NfNGUwtUJNR+lNhjNh8POe6DrTvM+mNk17m4p/vixdcDENw7kZhdPIytS/sMSxgMZuQwap2tyvXCItoJiHu2SvOF5lzeMUz+5tYEh2WeROq9Ea/ncZUaITBG7zmpJvJLG5cs79KNvIsfCnG2N31jj/N80Dl+j5mzl8u7vp/kEOfAnO+knJ6WHanbZ97mGNnTlaqMgvQun30PAgMBAAECggEAAlQ6tsLsV2YskbYK2J86aiKA9diU90kEnMUlXzxhRPZ5UyFo1oOUPVQevlr6AW5+fkgpwFVwHzdOsWabKei3APHrRo3jdG0TaiYUY0sKj6/oQ2XwZqPvy/OdzkvRaVNO0jnltjFmt2BIi4Ot5OQpQI6LExXzZw4YEeqfmLMxsG+5arSHWuld20gIYXlejiritkc9Qq4LjTXXxyti/AELWJLVBDWDHW4oz9X4dwF3JRupKjaH6hv6Ks5pShUqs59Z0b2B73pob9KCx/7KsGnX8fregA5qSl9NYOvNCs22E+3YORuw8ucucpZQ5zq39opTzlFqe6epOWkl0hraO+7eKQKBgQDqTreaMCL3K2ua8l7qsDFuJk2NtFD4M9qWboiB8CWJeMmCV39QoGu+WQGiQ5ENgcKZZKgA06WrHg6zKXK04YDYd7Ak6Gswr1sTq/wYvsUxNDQFHnjmbW5TlmjvDTpququ5jtLvc61cZlJdHk1RcZeMinKUeP9wVHgcILBDUUaw8wKBgQDEbeAUrLCDYOAnzfJxgmUDCd+CLHbjdm50prIeVjZanISVzGlCUhhs9cdjxSmzZ8qZiL8XaA3TvP2H5pCUmbQcYWP3NQVbBLNdqOIH19n+m0oRQFNxCFiUEqynbv6uBoOyAFHkH4oI3AVfs/R7gBoP2rDnM3afJcADCZ9UDsRqdQKBgGMALa21hyCetRG8NQS+u7QJ9RzZwpiM+GA1hfI5MPMcGTC8navIjJ3Z58nS+LgXSs0bba0jwE1EHvvumGv7GEDemYuOtLPWdAok0hj2ikVXBF/hVz4toL8qv126XiN4ilSWF2RboTV4enaj9hFiiKE2YmF/mpaw6isgWLmo3tCnAoGAS9hFEpaPXVsVkikCOPPpPuAcDi5PBYsgISiQVAhj2l7fDtwrtHDwf3nzMEHb1Sr5Xokfh9EkraImnsfXAZnLor20FKrDU56gB0UDaZXd1WJumfSlg1p+oUYIpmnEKrvXM+7Nr/DBoqQZ6xcYCmuvJHoDnnIPlmnEFygq/hDppFUCgYEA6Q9SFrQs+A2TXEMUBPUJ0siMKs5qU6Z0fBDjYkpRawn+hsNFGSsFcZy7TUB5wT/tRqpBE2OZqLwqodIlU8vRvr+20UUBGsZnytlX4bbL06IGcE2E8IYjvSBenN+sRn2DXzXja5Z5ACMcfQ+8zqWMDJzfif34hK7T05udqrXKBK4=",
                "json","utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk8+R/rXmW0G4mHi7YKblH98KHWn2vkhP3jHWzHgOBGl3YVVncentiXiOSvuvhgVlsnN48crq2dzqvlxeM7mMmsrNYRcePTe+3qPzYH+hka8WCFexPEccxeWInC7GSzdvULyYLGlZpx+1P31I0xBfsS9g3qfZeB9QM2KhNYD4rBNl0R3XZArGMQ4ZjK6rPg21QBeBdpTN3jMuN2+MOGtJHBofomtXWyrTeNLybwcdMFZZKDs6aH+Jmtxb2nGwL3M3FUcrKbcjiWntzmef4eeZxuf4S2vJd+Dx2fL4BDXuFHn5ClB15JmZ5q1klviRrEcLSL/Gyr6kW66MGqbuuWzOhwIDAQAB",  "RSA2" );

        //订单号  自定义
        String out_trade_no = ailpayRequest.getOrderNum();
//        String out_trade_no = "123456789124";
        out_trade_no = URLDecoder.decode(out_trade_no,"UTF-8");
        String total_amount = ailpayRequest.getMoney().toString();
//        String total_amount = "100.5";
        total_amount = URLDecoder.decode(total_amount,"UTF-8");
        String subject = ailpayRequest.getTitle();
//        String subject = "zhifu";
        subject = URLDecoder.decode(subject,"UTF-8");
        String body = "wu";
        body = URLDecoder.decode(body,"UTF-8");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //同步通知
        alipayRequest.setReturnUrl("http://localhost:8090/#/user/myOrder");
        //异步通知
        alipayRequest.setNotifyUrl("http://localhost:8090/#/user/myOrder");

        //配置参数
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+ out_trade_no +"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+ total_amount +"," +
                "    \"subject\":\""+ subject +"\"," +
                "    \"body\":\""+ body +"\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //form就是一个表单 html 直接给前端 替换 body标签里面的东西
        AilpayResponse ailpayResponse = new AilpayResponse();
        form = form.substring(0, form.indexOf("<script>"));
        ailpayResponse.setHtmlBody(form);
        result.setData(ailpayResponse);
        return result;
    }
}
