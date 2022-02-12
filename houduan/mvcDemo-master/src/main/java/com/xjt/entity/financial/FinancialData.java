package com.xjt.entity.financial;

import java.math.BigDecimal;

public class FinancialData {

    //商品种类名
    private String goodTypeName;

    //销量
    private Integer saleNum;

    //总销售额
    private BigDecimal saleMoney;

    //优惠金额
    private BigDecimal preferentialAmount;

    //实际销售额
    private BigDecimal realSaleMoney;

    public String getGoodTypeName() {
        return goodTypeName;
    }

    public void setGoodTypeName(String goodTypeName) {
        this.goodTypeName = goodTypeName;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public BigDecimal getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(BigDecimal preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public BigDecimal getRealSaleMoney() {
        return realSaleMoney;
    }

    public void setRealSaleMoney(BigDecimal realSaleMoney) {
        this.realSaleMoney = realSaleMoney;
    }
}
