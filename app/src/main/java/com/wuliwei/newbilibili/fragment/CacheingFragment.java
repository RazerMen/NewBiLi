package com.wuliwei.newbilibili.fragment;

import android.view.View;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseFragment;

/**
 * Created by
 * 武立伟
 * 2017/3/25.
 * <p>
 * 作用：缓存中界面
 */

public class CacheingFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_huancuning, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
