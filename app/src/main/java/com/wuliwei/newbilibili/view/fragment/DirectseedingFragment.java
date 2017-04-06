package com.wuliwei.newbilibili.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.HomeBean;
import com.wuliwei.newbilibili.presenter.adapter.HomeAdapter;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.view.base.BaseFragment1;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 82390 on 2017/3/22.
 */

public class DirectseedingFragment extends BaseFragment1 {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeAdapter adapter;

    public boolean isRefresh;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_directseeding, null);
        ButterKnife.bind(this, view);
        initRefresh();
        return view;
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
        return AppNet.BASE_URL;
    }

    @Override
    protected void initData(String json, String error) {
        if (TextUtils.isEmpty(json)) {
            Log.e("TAG", "DirectseedingFragment initData()" + error);
        } else {
//            JSONObject jsonObject = JSONObject.parseObject(json);
//            Integer code = jsonObject.getInteger("code");
            HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
            int code = homeBean.getCode();
            if (code == 0) {
                setAdapter(homeBean.getData());
            } else {
                Log.e("TAG", "联网失败");
            }
        }
    }

    private void setAdapter(HomeBean.DataBean data) {
        if (adapter == null) {
            adapter = new HomeAdapter(context, data);
        } else {
            adapter.notifyDataSetChanged();
    }
        if (!isRefresh) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            isRefresh = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
