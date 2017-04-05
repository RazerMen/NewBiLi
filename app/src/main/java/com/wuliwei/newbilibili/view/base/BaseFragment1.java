package com.wuliwei.newbilibili.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuliwei.newbilibili.presenter.LivePresenter;
import com.wuliwei.newbilibili.presenter.ResultListener;
import com.wuliwei.newbilibili.view.ILiveView;

/**
 * Created by 82390 on 2017/3/22.
 */

public abstract class BaseFragment1 extends Fragment implements ILiveView {

    public Context context;

    private boolean isShow;

    private LivePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        isShow = true;
        presenter = new LivePresenter(this);
        return initView();
    }

    public abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initNetData();
    }

    private void initNetData() {
        if (TextUtils.isEmpty(getUrl())) {
            initData(null, "url为空，无法请求数据");
            return;
        }
        presenter.getDataFromNet();
    }

    public void refresh() {
        initNetData();
    }

    protected void getDataFromNet(String url, ResultListener listener) {
        presenter.getDataFromNet(url, listener);
    }

    @Override
    public void onSucceed(String json) {
        initData(json, null);
    }

    @Override
    public void beDefeated(String error) {
        initData(null, error);
    }

    @Override
    public abstract void showLoading();

    @Override
    public abstract void hideLoading();

    @Override
    public abstract String getUrl();

    @Override
    public boolean isShowView() {
        return isShow;
    }

    protected abstract void initData(String json, String error);

}
