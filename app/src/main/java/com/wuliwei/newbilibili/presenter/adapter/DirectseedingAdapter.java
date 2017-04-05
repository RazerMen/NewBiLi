package com.wuliwei.newbilibili.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wuliwei.newbilibili.view.base.BaseFragment1;

import java.util.ArrayList;

/**
 * Created by 82390 on 2017/3/22.
 */

public class DirectseedingAdapter extends FragmentPagerAdapter {

    private final ArrayList<BaseFragment1> datas;
    private String[] titles = new String[]{"直播", "推荐", "追番", "分区", "发现"};

    public DirectseedingAdapter(FragmentManager fm, ArrayList<BaseFragment1> fragments) {
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
