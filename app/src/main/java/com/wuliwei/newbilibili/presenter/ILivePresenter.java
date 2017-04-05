package com.wuliwei.newbilibili.presenter;

/**
 * Created by
 * 武立伟
 * 2017/4/5.
 * <p>
 * 作用：
 */

public interface ILivePresenter {

    void onSuccess(String json);

    void onError(String error);

    void getDataFromNet();

    void cancelCall();

    void getDataFromNet(String url, ResultListener listener);

}
