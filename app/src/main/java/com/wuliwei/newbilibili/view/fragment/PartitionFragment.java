package com.wuliwei.newbilibili.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.FQDownBean;
import com.wuliwei.newbilibili.bean.FQUPBean;
import com.wuliwei.newbilibili.presenter.ResultListener;
import com.wuliwei.newbilibili.presenter.adapter.FQAdapter;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.view.base.BaseFragment1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 82390 on 2017/3/22.
 */

public class PartitionFragment extends BaseFragment1 {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FQUPBean fqupBean;
    private FQDownBean fqDownBean;

    private FQAdapter adapter;
    private List<FQUPBean.DataBean> upData;
    private List<FQDownBean.DataBean> downData;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_partition, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getUrl() {
        return AppNet.GV_URL;

    }

    @Override
    protected void initData(String json, String error) {
        if (TextUtils.isEmpty(json)) {
            Log.e("TAG", "PartitionFragment initData()" + error);
        } else {
            fqupBean = JSON.parseObject(json, FQUPBean.class);
            final int code = fqupBean.getCode();
            if (code == 0) {
                upData = fqupBean.getData();
                getDataFromNet(AppNet.FQDOWN_URL, new ResultListener() {
                    @Override
                    public void onSuccess(String json) {
                        fqDownBean = JSON.parseObject(json, FQDownBean.class);
                        int downCode = fqDownBean.getCode();
                        if (downCode == 0) {
                            downData = fqDownBean.getData();
                            setAdapter(upData, downData);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("TAG", "PartitionFragment onError()" + error);
                    }
                });
            }
        }
    }

    private void setAdapter(List<FQUPBean.DataBean> upData, List<FQDownBean.DataBean> downData) {
        if (adapter == null) {
            adapter = new FQAdapter(context, upData, downData);
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }
}