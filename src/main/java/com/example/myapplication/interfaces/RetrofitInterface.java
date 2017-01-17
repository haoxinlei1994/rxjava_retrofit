package com.example.myapplication.interfaces;

import com.example.myapplication.modle.BaseModle;
import com.example.myapplication.modle.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hao on 2017/1/16.
 */

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/userCenter/phoneExists.action")
    Observable<BaseModle<User>> login(@Field("phoneNo") String phoneNo);
}
