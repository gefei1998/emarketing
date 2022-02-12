package com.xjt.entity.goods;

public class AddCommendReplyRequest {

    //回复：1 删除：2
    private Integer replyFlag;

    //评论id
    private Integer commendId;

    //商家回复
    private String reply;

    public Integer getCommendId() {
        return commendId;
    }

    public void setCommendId(Integer commendId) {
        this.commendId = commendId;
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
