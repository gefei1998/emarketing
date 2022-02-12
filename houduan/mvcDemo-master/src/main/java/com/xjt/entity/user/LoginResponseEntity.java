package com.xjt.entity.user;

public class LoginResponseEntity {
    private int successFlag;

    private int userId;

    private String userName;

    public int getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(int successFlag) {
        this.successFlag = successFlag;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
