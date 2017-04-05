package com.wuliwei.newbilibili.presenter;

/**
 * Created by
 * 武立伟
 * 2017/4/5.
 * <p>
 * 作用：
 */

public interface ResultListener {
    //成功
    void onSuccess(String json);
    //失败
    void onError(String error);
}
