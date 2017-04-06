package com.wuliwei.newbilibili.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.JFBean;
import com.wuliwei.newbilibili.presenter.adapter.ZFAdapter;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.view.base.BaseFragment1;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 82390 on 2017/3/22.
 */

public class TrackFragment extends BaseFragment1 {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ZFAdapter adapter;

    public boolean isRefresh;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_track, null);
        ButterKnife.bind(this, view);
        initRefresh();
        return view;
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getUrl() {
        return AppNet.ZF_URL;
    }

    @Override
    protected void initData(String json, String error) {
        if (TextUtils.isEmpty(json)) {
            Log.e("TAG", "TrackFragment initData()" + error);
        } else {
            JFBean jfBean = JSON.parseObject(json, JFBean.class);
            Log.e("TAG", "解析成功99999==" + jfBean.getResult().getAd().getHead().get(0).getTitle());
            int code = jfBean.getCode();
            if (code == 0) {
                setAdapter(jfBean.getResult());
            } else {
                Log.e("TAG", "联网失败");
            }
        }
    }

    private void setAdapter(JFBean.ResultBean result) {
        if (adapter == null) {
            adapter = new ZFAdapter(context, result);
        } else {
            adapter.notifyDataSetChanged();
        }
        if (!isRefresh) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        } else {
            isRefresh = false;
        }
    }

    private void initRefresh() {

        //设置刷新的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        //设置下拉刷新的监听
        swipeRefreshLayout.setOnRefreshListener(new MyOnRefreshListener());

    }

    class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            isRefresh = true;
            refresh();
        }
    }
}
