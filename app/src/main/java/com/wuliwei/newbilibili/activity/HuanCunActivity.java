package com.wuliwei.newbilibili.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.HuanCunAdapter;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.fragment.CacheFragment;
import com.wuliwei.newbilibili.fragment.CacheingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HuanCunActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_guanli)
    TextView tvGuanli;
    @BindView(R.id.iv_game)
    ImageView ivGame;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private List<BaseFragment> fragments;

    private HuanCunAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        initFragment();
        initAdapter();

    }

    private void initAdapter() {

        adapter = new HuanCunAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new CacheFragment());
        fragments.add(new CacheingFragment());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_huan_cun;
    }

    @OnClick({R.id.iv_back, R.id.iv_game, R.id.iv_down, R.id.iv_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_game:
                Toast.makeText(HuanCunActivity.this, "管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_down:
                Toast.makeText(HuanCunActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_select:
                Toast.makeText(HuanCunActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
