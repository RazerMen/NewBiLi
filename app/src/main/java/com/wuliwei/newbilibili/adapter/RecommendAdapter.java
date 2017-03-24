package com.wuliwei.newbilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wuliwei.newbilibili.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：
 */

public class RecommendAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> datas;
    private String[] titles = new String[]{"综合", "动态"};

    public RecommendAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.datas = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
