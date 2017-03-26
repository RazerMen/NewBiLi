package com.wuliwei.newbilibili.app;


import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by
 * 武立伟
 * 2017/3/26.
 * <p>
 * 作用：
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);

    }
}
