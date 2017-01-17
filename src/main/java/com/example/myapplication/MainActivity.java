package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.modle.BaseModle;
import com.example.myapplication.modle.User;
import com.example.myapplication.utils.RetrofitUtils;
import com.example.myapplication.utils.TransformUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.Observers;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private int flat = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
    }

    /**
     * 假设后台服务器数据的返回格式是{int code ,String message, T data}
     * @param view
     */
    public void getData(View view) {
        RetrofitUtils.getDefault().login("13832453426")
               /* compose:对返的数据进行预先处理：
                code=0：数据请求正常，将Observable<BaseModle<T>>转化为Observable<T>,并发射给订阅者
                code！=0：数据请求失败，将根据返回数据的message给用户信息提示*/
                .compose(TransformUtils.<User>handleResult())
                //自定义一个观察者
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        //更新UI
                    }
                });
    }
}
