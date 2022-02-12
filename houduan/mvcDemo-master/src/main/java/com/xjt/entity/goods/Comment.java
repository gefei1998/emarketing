package com.xjt.entity.goods;

public class Comment {
    //留言id
    private Integer commendId;
    //留言内容
    private String comment;
    //留言人
    private String userName;
    //留言时间
    private String commendTime;

    //订单编号
    private String orderNum;

    //用户头像
    private String userPath;

    //是否是用户自己的留言，1：是 0：否
    private Integer flag;

    //商家回复
    private String reply;

    //商家回复
    private String replyTime;

    public Integer getCommendId() {
        return commendId;
    }

    public void setCommendId(Integer commendId) {
        this.commendId = commendId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommendTime() {
        return commendTime;
    }

    public void setCommendTime(String commendTime) {
        this.commendTime = commendTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
