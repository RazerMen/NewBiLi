package com.wuliwei.newbilibili.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.wuliwei.newbilibili.presenter.LivePresenter;
import com.wuliwei.newbilibili.view.ILiveView;

import butterknife.ButterKnife;

/**
 * Created by 82390 on 2017/3/22.
 */

public abstract class BaseActivity1 extends AppCompatActivity implements ILiveView{

    private boolean isShow;
    private LivePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = new LivePresenter(this);
        isShow = true;
        initNetData();
        initListener();
    }

    private void initNetData() {
        if(TextUtils.isEmpty(getUrl())) {
            initData(null,"url为空，无法请求数据");
            return;
        }
    }
    protected void refresh() {
        initNetData();
    }

    public abstract String getUrl();

    @Override
    public final boolean isShowView() {
        return isShow;
    }

    protected abstract void initData(String json, String error);

    protected abstract void initListener();

    public abstract int getLayoutId();

    @Override
    public void onSucceed(String json) {
        initData(json,null);
    }

    @Override
    public void beDefeated(String error) {
        initData(null,error);
    }

    @Override
    public abstract void showLoading();

    @Override
    public abstract void hideLoading();

}
