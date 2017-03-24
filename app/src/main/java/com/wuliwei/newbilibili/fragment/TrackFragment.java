package com.wuliwei.newbilibili.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.ZFAdapter;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.JFBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 82390 on 2017/3/22.
 */

public class TrackFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ZFAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_track, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

        getDataFromNet();

    }

    private void getDataFromNet() {

        OkHttpUtils.get().url(AppNet.ZF_URL).id(100).build().execute(new StringCallback() {
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
        JFBean jfBean = JSON.parseObject(json, JFBean.class);
        Log.e("TAG", "解析成功99999==" + jfBean.getResult().getAd().getHead().get(0).getTitle());

        JFBean.ResultBean result = jfBean.getResult();

        //设置适配器
        adapter = new ZFAdapter(context, result);
        recyclerView.setAdapter(adapter);

        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

    }
}
