package com.wuliwei.newbilibili.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wuliwei.newbilibili.base.BaseFragment;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：番剧的Fragment
 */

public class FanjuFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("番剧");
    }
}
