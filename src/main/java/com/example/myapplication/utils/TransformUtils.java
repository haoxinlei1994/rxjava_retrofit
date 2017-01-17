package com.example.myapplication.utils;

import com.example.myapplication.modle.BaseModle;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by hao on 2017/1/16.
 */

public class TransformUtils {
    public static <T> Observable.Transformer<BaseModle<T>,T> handleResult(){
        /*Transformer 实际上就是一个 Func1<Observable<T>, Observable<R>>，
        换言之就是：可以通过它将一种类型的 Observable 转换成另一种类型的 Observable，
        和调用一系列的内联操作符是一模一样的。*/
        return new Observable.Transformer<BaseModle<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseModle<T>> baseModleObservable) {
                return baseModleObservable.flatMap(new Func1<BaseModle<T>,Observable<T>>(){

                    @Override
                    public Observable<T> call(BaseModle<T> tBaseModle) {
                        if(tBaseModle.getCode()==0){
                            //如果获取数据成功
                            return createData(tBaseModle.getData());
                        }else {
                            //如果请求失败,根据返回信息给用户不同的提示
                            return Observable.error(new RetrofitException(tBaseModle.getMessage()));
                        }

                    }
                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable<T> createData(final T data){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try{
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        });
    }

}
