package com.example.myapplication.utils;

/**
 * Created by hao on 2017/1/16.
 */

public class RetrofitException extends RuntimeException{
    private String message;
    public RetrofitException(String msg){
        message = msg;
    }
    @Override
    public  String getMessage() {
        return message;
    }
}
