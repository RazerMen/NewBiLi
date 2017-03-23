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
 * 作用：原创的ViewPager的适配器
 */

public class OriginalAdapter extends FragmentPagerAdapter {

    private final ArrayList<BaseFragment> fragments;

    private String[] titles = new String[]{"原创","全站","番剧"};

    public OriginalAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
