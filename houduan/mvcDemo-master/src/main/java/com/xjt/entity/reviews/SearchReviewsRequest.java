package com.xjt.entity.reviews;

public class SearchReviewsRequest {
    //用户id
    private Integer userId;

    //是否查看自己的留言 1:是 0：否
    private Integer searchFlag;

    //用户名
    private String userName;

    //每页数量
    private Integer pageNum;

    //页码
    private Integer page;

    private Integer pageStart;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(Integer searchFlag) {
        this.searchFlag = searchFlag;
    }
}
