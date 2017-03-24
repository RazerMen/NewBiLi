package com.wuliwei.newbilibili.fragment;

import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.ZHAdapter;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.ZHBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.view.MyGridView;
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
    MyGridView gridView;

    private ZHAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_comprehensive, null);
        ButterKnife.bind(this, view);
        return view;
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
                Log.e("TAG", "成功 ");
                proceessData(response);
            }
        });
    }

    private void proceessData(String json) {
        ZHBean zhBean = JSON.parseObject(json,ZHBean.class);
        Log.e("TAG", "解析成功678==" + zhBean.getData().get(1).getTitle());

        List<ZHBean.DataBean> data = zhBean.getData();

        //设置适配器
        if(data != null && data.size() > 0) {

            adapter = new ZHAdapter(context,data);
            gridView.setAdapter(adapter);

        }
    }
}
