package com.xjt.entity.order;

import java.math.BigDecimal;
import java.util.List;

public class SearchOrderResponse {
    //订单id
    private Integer orderId;

    //订单编号
    private String orderNum;

    //用户名
    private String userName;

    //交易额
    private BigDecimal money;

    //获得的积分
    private Integer integral;

    //参与的活动名
    private String activeName;

    //优惠金额
    private BigDecimal preferentialAmount;

    //订单时间
    private String orderTime;

    //用户真实姓名
    private String realName;

    //用户手机号
    private String telephone;

    //用户地址
    private String address;

    //商品list
    private List<SearchOrderGood> goodList;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public BigDecimal getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(BigDecimal preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public List<SearchOrderGood> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<SearchOrderGood> goodList) {
        this.goodList = goodList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
