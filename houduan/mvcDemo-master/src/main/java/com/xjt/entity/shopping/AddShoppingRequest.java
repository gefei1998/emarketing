package com.xjt.entity.shopping;

public class AddShoppingRequest {

    //商品
    private Integer goodId;
    //用户id
    private Integer userId;

    //购物车数量
    private Integer goodNum;

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }
}
