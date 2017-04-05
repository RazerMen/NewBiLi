package com.wuliwei.newbilibili.model;

import com.wuliwei.newbilibili.presenter.ResultListener;

/**
 * Created by
 * 武立伟
 * 2017/4/4.
 * <p>
 * 作用：
 */

public interface ILiveModel {

    void getDataFromNet(String url);

    void cancelCall();

    void getDataFromNet(String url,ResultListener listener);

}
