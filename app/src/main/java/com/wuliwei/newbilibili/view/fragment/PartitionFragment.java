package com.wuliwei.newbilibili.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.presenter.adapter.FQAdapter;
import com.wuliwei.newbilibili.view.base.BaseFragment;
import com.wuliwei.newbilibili.bean.FQDownBean;
import com.wuliwei.newbilibili.bean.FQUPBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 82390 on 2017/3/22.
 */

public class PartitionFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<FQUPBean.DataBean> upData;
    private List<FQDownBean.DataBean> downData;

    private FQUPBean fqupBean;
    private FQDownBean fqDownBean;

    private FQAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_partition, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();
        getDataFromDownNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNet.GV_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "成功 ");
                proceessData(response);
            }
        });
    }

    private void proceessData(String json) {
        fqupBean = JSON.parseObject(json, FQUPBean.class);
        upData = fqupBean.getData();
        Log.e("TAG", "up解析成功11111" + upData.get(0).getName());
        initAdapter();
    }

    private void getDataFromDownNet() {
        OkHttpUtils.get().url(AppNet.FQDOWN_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "成功 ");
                analysisData(response);
            }
        });
    }

    private void analysisData(String json) {
        fqDownBean = JSON.parseObject(json, FQDownBean.class);
        downData = fqDownBean.getData();
        Log.e("TAG", "down解析成功22222" + downData.get(0).getTitle());
        initAdapter();
    }


    private void initAdapter() {

        if(upData != null && downData != null) {
            //设置适配器
            adapter = new FQAdapter(context, upData, downData);
            recyclerView.setAdapter(adapter);
        }
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }
}
