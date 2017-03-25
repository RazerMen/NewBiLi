package com.wuliwei.newbilibili.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.OriginalAdapter;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.fragment.FanjuFragment;
import com.wuliwei.newbilibili.fragment.OriginalFragment;
import com.wuliwei.newbilibili.fragment.QuanZhanFragment;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class OriginalActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_paihang)
    TextView tvPaihang;
    @BindView(R.id.iv_xiazai)
    ImageView ivXiazai;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
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

    private ArrayList<BaseFragment> fragments;

    private OriginalAdapter adapter;

    private SearchFragment searchFragment;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        initFragment();
        initAdapter();
        searchFragment = SearchFragment.newInstance();
    }

    private void initAdapter() {

        adapter = new OriginalAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new OriginalFragment());
        fragments.add(new QuanZhanFragment());
        fragments.add(new FanjuFragment());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_original;
    }

    @OnClick({R.id.iv_back, R.id.iv_xiazai, R.id.iv_sousuo, R.id.toolBar, R.id.tabLayout, R.id.appBar, R.id.coordinatorLayout, R.id.drawerLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_xiazai:
                Toast.makeText(OriginalActivity.this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_sousuo:
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
//                Toast.makeText(ToolBarActivity.this, keyword, Toast.LENGTH_SHORT).show();
                    }
                });
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
            case R.id.toolBar:
                break;
            case R.id.tabLayout:
                break;
            case R.id.appBar:
                break;
            case R.id.coordinatorLayout:
                break;
            case R.id.drawerLayout:
                break;
        }
    }
}
