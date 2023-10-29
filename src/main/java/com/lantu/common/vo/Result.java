package com.lantu.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//主要负责用于和前端对接的接口类的实现
public class Result<T> {
    private  Integer code;
    private String message;
    private  T data;

    public  static <T> Result<T> success(){
        return new Result<>(20000,"success",null);
    }

    public  static <T> Result<T> success(T data){
        return new Result<>(20000,"success",data);
    }
    public  static <T> Result<T> success(T data,String message){
        return new Result<>(20000,message,data);
    }

    public  static <T> Result<T> success(String message){
        return new Result<>(20000,message,null);
    }

    public  static <T> Result<T> error(T data,String message){
        return new Result<>(20001,message,data);
    }
    public  static <T> Result<T> error(int code,String message){
        return new Result<>(20001,message,null);
    }
    public  static <T> Result<T> error(T data){
        return new Result<>(20001,"fail",data);
    }

    public  static <T> Result<T> error(String message){
        return new Result<>(20001,message,null);
    }





}
