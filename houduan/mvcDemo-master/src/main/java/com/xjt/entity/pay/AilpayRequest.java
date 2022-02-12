package com.xjt.entity.pay;

import java.math.BigDecimal;

public class AilpayRequest {
    //金额
    private BigDecimal money;

    //订单编号
    private String orderNum;

    //支付标题
    private String title;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
