package com.xjt.entity.goods;

import java.math.BigDecimal;

public class AddRequestEntity {
    //商品名称
    private String goodName;
    //商品图片
    private String goodPath;
    //商品价格
    private BigDecimal goodPrice;
    //商品类别
    private Integer goodType;

    //商品描述
    private String goodDescription;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodPath() {
        return goodPath;
    }

    public void setGoodPath(String goodPath) {
        this.goodPath = goodPath;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Integer getGoodType() {
        return goodType;
    }

    public void setGoodType(Integer goodType) {
        this.goodType = goodType;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }
}
