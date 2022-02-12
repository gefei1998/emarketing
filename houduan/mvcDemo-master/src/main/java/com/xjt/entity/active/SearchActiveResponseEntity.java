package com.xjt.entity.active;

import java.math.BigDecimal;

public class SearchActiveResponseEntity {
    //活动名
    private String activeName;

    //活动简介
    private String activeContent;

    //满减 （满）
    private BigDecimal fullPrice;

    //满减 （减）
    private BigDecimal minusPrice;

    //活动图片
    private String activePath;

    //活动id
    private Integer activeId;

    //是否启用 0：启用 1：禁用
    private Integer deleteFlag;

    //更新时间
    private String updateTime;

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getMinusPrice() {
        return minusPrice;
    }

    public void setMinusPrice(BigDecimal minusPrice) {
        this.minusPrice = minusPrice;
    }

    public String getActivePath() {
        return activePath;
    }

    public void setActivePath(String activePath) {
        this.activePath = activePath;
    }

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
