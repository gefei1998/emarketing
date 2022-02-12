package com.xjt.entity.reviews;

public class AddReviewReplyRequest {
    //回复：1 删除：2
    private Integer replyFlag;

    private Integer reviewId;

    private String reply;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Integer replyFlag) {
        this.replyFlag = replyFlag;
    }
}
