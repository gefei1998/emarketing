package com.xjt.entity.order;

import java.math.BigDecimal;
import java.util.List;

public class AddOrderRequest {

    //订单id
    private Integer orderId;

    //订单编号
    private Long orderNum;

    //用户id
    private Integer userId;

    //交易额
    private BigDecimal money;

    //获得的积分
    private Integer integral;

    //参与的活动id
    private Integer activeId;

    //优惠金额
    private BigDecimal preferentialAmount;

    //商品list
    private List<AddOrderGood> goodList;

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public BigDecimal getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(BigDecimal preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public List<AddOrderGood> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<AddOrderGood> goodList) {
        this.goodList = goodList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
