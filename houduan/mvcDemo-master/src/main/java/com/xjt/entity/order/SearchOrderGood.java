package com.xjt.entity.order;

import java.math.BigDecimal;

public class SearchOrderGood {

    //商品id
    private Integer goodId;

    //商品名
    private String goodName;

    //商品图片路径
    private String goodPath;

    //商品价格
    private BigDecimal goodPrice;

    //商品种类
    private Integer goodType;

    //商品销量
    private Integer goodNum;

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

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
}
