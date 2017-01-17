package com.example.myapplication.utils;

import com.example.myapplication.interfaces.RetrofitInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 通过retrofitUtils工具类，获取接口的实例化对象
 * Created by hao on 2017/1/16.
 */

public class RetrofitUtils {
    //获取接口的实例对象
    private static RetrofitInterface apiService;
    public static final String BASE_URL = "http://www.crazylaw.net:88/";

    /**
     * 使用单例模式获取借口的实例化对象
     * @return
     */
    public static RetrofitInterface getDefault(){
        if(apiService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //添加格式转换工厂，用于请求回来的数据格式转化，此处自动转为Gson格式（json自动解析）
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加后就才可以返回Observable对象
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            apiService = retrofit.create(RetrofitInterface.class);
        }
        return apiService;
    }
}
