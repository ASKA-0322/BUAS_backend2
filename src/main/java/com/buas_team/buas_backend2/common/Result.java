package com.buas_team.buas_backend2.common;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public Result(){}

    public Result(T data) {
        this.data = data;
    }

    public static <T>Result<T> sucess(){
        Result result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }
    public static <T> Result<T> sucess(T data){
        Result<T> result = new Result<>(data);
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }
    public static <T>Result<T> error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}