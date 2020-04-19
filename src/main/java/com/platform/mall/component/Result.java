package com.platform.mall.component;

import java.io.Serializable;

//统一返回数据格式
public class Result<T> {

    private int code;
    private String message;
    private T data;

    private Result(){
    }

    private Result(int code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static  <T> Result<T> success(T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    public static  <T> Result<T> success(String message,T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),message,data);
    }

    public static  <T> Result<T> failed(String message){
        return new Result<T>(ResultCode.FAILED.getCode(),message,null);
    }

    public static  <T> Result<T> failed(T data){
        return new Result<T>(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage(),data);
    }

    public static  <T> Result<T> failed(String message,T data){
        return new Result<T>(ResultCode.FAILED.getCode(),message,data);
    }

    public static  <T> Result<T> timeOut(){
        return new Result<T>(ResultCode.TIME_OUT.getCode(),ResultCode.TIME_OUT.getMessage(),null);
    }

    public static  <T> Result<T> timeOut(String message){
        return new Result<T>(ResultCode.TIME_OUT.getCode(),message,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
