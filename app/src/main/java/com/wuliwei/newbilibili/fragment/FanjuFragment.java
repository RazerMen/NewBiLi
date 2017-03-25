package com.wuliwei.newbilibili.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.FJAdapter;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.FJBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：番剧的Fragment
 */

public class FanjuFragment extends BaseFragment {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private FJAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_yuanchuang, null);
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

    @Override
    public void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNet.FJ_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "成功 ");
                proceessData(response);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void proceessData(String json) {
        FJBean fjBean = JSON.parseObject(json, FJBean.class);
        Log.e("TAG", "解析成功==" + fjBean.getData().get(0).getTitle());

        List<FJBean.DataBean> data = fjBean.getData();

        //设置适配器
        adapter = new FJAdapter(context, data);
        listView.setAdapter(adapter);
    }

    class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getDataFromNet();
                }
            }, 2000);
        }
    }
}
