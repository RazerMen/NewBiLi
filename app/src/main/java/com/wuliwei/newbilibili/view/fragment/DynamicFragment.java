package com.wuliwei.newbilibili.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.view.activity.LoginActivity;
import com.wuliwei.newbilibili.view.base.BaseFragment1;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：动态的Fragment
 */

public class DynamicFragment extends BaseFragment1 {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_dynamic, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected void initData(String json, String error) {

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }
}
