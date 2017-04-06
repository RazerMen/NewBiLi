package com.wuliwei.newbilibili.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.ZHBean;
import com.wuliwei.newbilibili.presenter.adapter.ZHAdapter;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.view.base.BaseFragment1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：综合的Fragment
 */

public class ComprehensiveFragment extends BaseFragment1 {
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ZHAdapter adapter;

    public boolean isRefresh;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_comprehensive, null);
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
        return AppNet.ZH_URL;
    }

    @Override
    protected void initData(String json, String error) {
        if (TextUtils.isEmpty(json)) {
            Log.e("TAG", "ComprehensiveFragment initData()" + error);
        } else {
            ZHBean zhBean = JSON.parseObject(json, ZHBean.class);
            int code = zhBean.getCode();
            if (code == 0) {
                setAdapter(zhBean.getData());
            } else {
                Log.e("TAG", "联网失败");
            }
        }
    }

    private void setAdapter(List<ZHBean.DataBean> data) {
        if (adapter == null) {
            adapter = new ZHAdapter(context, data);
        } else {
            adapter.notifyDataSetChanged();
        }
        if (!isRefresh) {
            gridView.setAdapter(adapter);
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
//
//    @Override
//    public void initData() {
//        getDataFromNet();
//
//    }
//
//    private void getDataFromNet() {
//        OkHttpUtils.get().url(AppNet.ZH_URL).id(100).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e("TAG", "失败" + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.e("TAG", "成功123123123123123 ");
//                proceessData(response);
//                //隐藏刷新
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//    }

//    private void proceessData(String json) {
//        ZHBean zhBean = JSON.parseObject(json, ZHBean.class);
//        Log.e("TAG", "解析成功678==" + zhBean.getData().get(1).getTitle());
//
//        List<ZHBean.DataBean> data = zhBean.getData();
//
//        //设置适配器
//        if (data != null && data.size() > 0) {
//
//            adapter = new ZHAdapter(context, data);
//            gridView.setAdapter(adapter);
//
//        }
//    }

    class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            isRefresh = true;
            refresh();
        }
    }
}
