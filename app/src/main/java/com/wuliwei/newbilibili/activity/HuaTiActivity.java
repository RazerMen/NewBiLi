package com.wuliwei.newbilibili.activity;

import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.bean.HuaTiBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class HuaTiActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.listView)
    ListView listView;

    private List<HuaTiBean.ListBean> list;
    private HuaTiAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNet.HUATI_URL).id(100).build().execute(new StringCallback() {
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
        HuaTiBean huaTiBean = JSON.parseObject(json, HuaTiBean.class);
        Log.e("TAG", "解析成功" + huaTiBean.getList());
        list = huaTiBean.getList();

        if(list != null && list.size() > 0) {
            //设置适配器
            adapter = new HuaTiAdapter(this, list);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hua_ti;
    }

    @OnClick(R.id.ib_back)
    public void onClick() {
        finish();
    }
}
