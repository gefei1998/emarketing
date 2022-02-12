package com.xjt.entity.goods;

import java.math.BigDecimal;

public class UpdateGoodsRequestEntity {
    //商品名称
    private String goodName;
    //商品图片
    private String goodPath;
    //商品价格
    private BigDecimal goodPrice;
    //商品类别
    private Integer goodType;
    //商品id
    private Integer goodId;
    //是否启用 0：启用 1：禁用
    private Integer deleteFlag;
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

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }
}
