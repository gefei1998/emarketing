package com.xjt.entity.recommend;

public class AddRecommendRequest {
    private String goodIds;

    private Integer activeId;

    public String getGoodIds() {
        return goodIds;
    }

    public void setGoodIds(String goodIds) {
        this.goodIds = goodIds;
    }

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }
}
