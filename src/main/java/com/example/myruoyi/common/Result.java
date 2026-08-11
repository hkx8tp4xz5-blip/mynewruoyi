package com.example.myruoyi.common;

public class Result {              // 返回的结果
    private static final long serialVersionUID = 1L; // 序列化版本号
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setData(data);
        result.setMsg("操作成功!");
        return result;
    }
    public static Result fail(String msg){
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}

