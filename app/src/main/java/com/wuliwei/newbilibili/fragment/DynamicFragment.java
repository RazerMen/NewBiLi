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
 * 作用：动态的Fragment
 */

public class DynamicFragment extends BaseFragment {

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
        textView.setText("动态");
    }
}
