package com.xjt.entity.active;

public class SearchActiveRequestEntity {
    //活动id
    private Integer activeId;

    //活动名
    private String activeName;

    //是否启用 0：启用 1：禁用
    private Integer deleteFlag;

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
