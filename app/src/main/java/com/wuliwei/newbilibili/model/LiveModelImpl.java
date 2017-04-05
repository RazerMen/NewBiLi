package com.wuliwei.newbilibili.model;

import com.wuliwei.newbilibili.presenter.ILivePresenter;
import com.wuliwei.newbilibili.presenter.ResultListener;
import com.wuliwei.newbilibili.uitls.NetUtils;
import com.zhy.http.okhttp.request.RequestCall;

/**
 * Created by
 * 武立伟
 * 2017/4/4.
 * <p>
 * 作用：
 */

public class LiveModelImpl implements ILiveModel {

    private final ILivePresenter presenter;

    private RequestCall requestCall;

    public LiveModelImpl(ILivePresenter iLivePresenter) {
        this.presenter = iLivePresenter;
    }

    @Override
    public void getDataFromNet(String url) {
        requestCall = NetUtils.getInstance().getOkHttpUtils(url, new NetUtils.resultJson() {
            @Override
            public void onResponse(String json) {
                if (presenter != null) {
                    presenter.onSuccess(json);
                }
            }

            @Override
            public void onError(String error) {
                if (presenter != null) {
                    presenter.onError(error);
                }
            }
        });
    }

    @Override
    public void cancelCall() {
        if (requestCall != null) {
            requestCall.cancel();
        }
    }

    @Override
    public void getDataFromNet(String url, final ResultListener listener) {
        requestCall = NetUtils.getInstance().getOkHttpUtils(url, new NetUtils.resultJson() {
            @Override
            public void onResponse(String json) {
                if (listener != null) {
                    listener.onSuccess(json);
                }
            }

            @Override
            public void onError(String error) {
                if (listener != null) {
                    listener.onError(error);
                }
            }
        });
    }
}
