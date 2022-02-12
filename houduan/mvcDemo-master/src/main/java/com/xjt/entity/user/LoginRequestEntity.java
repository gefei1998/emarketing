package com.xjt.entity.user;

public class LoginRequestEntity {
    //用户名
    private String userName;
    //用户密码
    private String password;

    //权限 0：管理员 1：用户
    private int operation;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
}
