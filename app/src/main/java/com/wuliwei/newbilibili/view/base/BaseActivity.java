package com.wuliwei.newbilibili.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;

import butterknife.ButterKnife;

/**
 * Created by 82390 on 2017/3/22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initData();

    public abstract int getLayoutId();
}
