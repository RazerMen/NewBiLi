package com.wuliwei.newbilibili.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.YCAdapter;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.YCBean;
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
 * 作用：全站的Fragment
 */

public class QuanZhanFragment extends BaseFragment {


    @BindView(R.id.listView)
    ListView listView;

    private YCAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_yuanchuang, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNet.YC_URL).id(100).build().execute(new StringCallback() {
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
        YCBean ycBean = JSON.parseObject(json,YCBean.class);
        Log.e("TAG", "解析成功==" + ycBean.getData().get(0).getTitle());

        List<YCBean.DataBean> data = ycBean.getData();

        //设置适配器
        adapter = new YCAdapter(context,data);
        listView.setAdapter(adapter);
    }
}
