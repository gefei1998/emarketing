package com.xjt.entity.goods;

import java.math.BigDecimal;
import java.util.List;

public class SearchGoodsRequestEntity {
    //0：用户 1：管理员
    private Integer flag;

    //用户id
    private Integer userId;

    //商品id
    private Integer goodId;

    //商品类别
    private List<Integer> goodType;

    //商品名
    private String goodName;

    //最小价格
    private BigDecimal smallPrice;

    //最大价格
    private BigDecimal bigPrice;

    //是否启用  0：启用 1：禁用
    private Integer deleteFlag;

    //每页数量
    private Integer pageNum;

    //页码
    private Integer page;

    private Integer pageStart;

    public List<Integer> getGoodType() {
        return goodType;
    }

    public void setGoodType(List<Integer> goodType) {
        this.goodType = goodType;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(BigDecimal smallPrice) {
        this.smallPrice = smallPrice;
    }

    public BigDecimal getBigPrice() {
        return bigPrice;
    }

    public void setBigPrice(BigDecimal bigPrice) {
        this.bigPrice = bigPrice;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
}
