package com.wuliwei.newbilibili.view.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wuliwei.newbilibili.view.base.BaseFragment;

/**
 * Created by
 * 武立伟
 * 2017/3/28.
 * <p>
 * 作用：
 */

public class FJuragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("番剧");
    }
}
