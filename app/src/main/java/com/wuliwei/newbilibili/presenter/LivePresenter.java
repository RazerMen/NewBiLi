package com.wuliwei.newbilibili.presenter;

import com.wuliwei.newbilibili.model.LiveModelImpl;
import com.wuliwei.newbilibili.view.ILiveView;

/**
 * Created by
 * 武立伟
 * 2017/4/4.
 * <p>
 * 作用：
 */

public class LivePresenter implements ILivePresenter {

    private final ILiveView view;
    private final LiveModelImpl model;

    public LivePresenter(ILiveView iLiveView) {
        this.view = iLiveView;
        this.model = new LiveModelImpl(this);
    }

    @Override
    public void onSuccess(String json) {
        if (view.isShowView()) {
            view.hideLoading();
            view.onSucceed(json);
        }
    }

    @Override
    public void onError(String error) {
        if (view.isShowView()) {
            view.hideLoading();
            view.beDefeated(error);
        }
    }

    @Override
    public void getDataFromNet() {
        if (view.isShowView()) {
            view.showLoading();
            model.getDataFromNet(view.getUrl());
        }
    }

    @Override
    public void cancelCall() {
        model.cancelCall();
    }

    @Override
    public void getDataFromNet(String url, ResultListener listener) {
        model.getDataFromNet(url, listener);
    }
}
