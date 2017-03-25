package com.wuliwei.newbilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wuliwei.newbilibili.base.BaseFragment;

import java.util.List;

/**
 * Created by
 * 武立伟
 * 2017/3/25.
 * <p>
 * 作用：缓存列表的适配器
 */

public class HuanCunAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> datas;
    private String[] titles = new String[]{"已缓存","缓存中"};

    public HuanCunAdapter(FragmentManager fm, List<BaseFragment> fragments) {
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
