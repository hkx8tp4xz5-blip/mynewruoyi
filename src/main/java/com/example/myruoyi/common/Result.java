package com.example.myruoyi.common;

public class Result {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final String msg;
    private final Object data;
    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
