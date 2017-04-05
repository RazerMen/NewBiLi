package com.wuliwei.newbilibili.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.presenter.adapter.RecommendAdapter;
import com.wuliwei.newbilibili.view.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 82390 on 2017/3/22.
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragments;

    private RecommendAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        initFragnment();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new RecommendAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragnment() {
        fragments = new ArrayList<>();
        fragments.add(new ComprehensiveFragment());
        fragments.add(new DynamicFragment());
    }
}
