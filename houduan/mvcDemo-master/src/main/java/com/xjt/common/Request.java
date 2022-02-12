package com.xjt.common;

public class Request<T> {
    private T data;

    public Request(T data){
        this.data = data;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
