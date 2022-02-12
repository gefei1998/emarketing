package com.xjt.entity.reviews;

public class SearchReviewsResponse {

    //留言id
    private Integer reviewsId;
    //留言内容
    private String reviewsComment;
    //留言人
    private String userName;
    //留言人id
    private Integer userId;
    //留言时间
    private String createTime;

    //用户头像
    private String userPath;

    //是否是用户自己的留言，1：是 0：否
    private Integer flag;

    //商家回复
    private String reply;

    //回复时间
    private String replyTime;

    public Integer getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(Integer reviewsId) {
        this.reviewsId = reviewsId;
    }

    public String getReviewsComment() {
        return reviewsComment;
    }

    public void setReviewsComment(String reviewsComment) {
        this.reviewsComment = reviewsComment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
