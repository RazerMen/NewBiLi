package com.wuliwei.newbilibili.view.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.ZHBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.presenter.adapter.ZHAdapter;
import com.wuliwei.newbilibili.view.base.BaseFragment;
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
 * 作用：综合的Fragment
 */

public class ComprehensiveFragment extends BaseFragment {
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ZHAdapter adapter;

//    private LivePresenter presenter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_comprehensive, null);
        ButterKnife.bind(this, view);

//        presenter = new LivePresenter(this);

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
        OkHttpUtils.get().url(AppNet.ZH_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "成功123123123123123 ");
                proceessData(response);
                //隐藏刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void proceessData(String json) {
        ZHBean zhBean = JSON.parseObject(json, ZHBean.class);
        Log.e("TAG", "解析成功678==" + zhBean.getData().get(1).getTitle());

        List<ZHBean.DataBean> data = zhBean.getData();

        //设置适配器
        if (data != null && data.size() > 0) {

            adapter = new ZHAdapter(context, data);
            gridView.setAdapter(adapter);

        }
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
