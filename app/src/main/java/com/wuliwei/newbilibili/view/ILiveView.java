package com.wuliwei.newbilibili.view;

/**
 * Created by
 * 武立伟
 * 2017/4/4.
 * <p>
 * 作用：
 */

public interface ILiveView {

    //成功的时候回调
    void onSucceed(String json);
    //失败的时候回调
    void beDefeated(String error);

    String getUrl();

    void showLoading();

    void hideLoading();

    boolean isShowView();
}
