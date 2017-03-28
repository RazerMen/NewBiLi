package com.wuliwei.newbilibili.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.ShoppingAdapter;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.ShoppingHomeBean;
import com.wuliwei.newbilibili.bean.ShoppingHomeDownBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by
 * 武立伟
 * 2017/3/27.
 * <p>
 * 作用：购物车主页
 */

public class HomeShoppingFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ShoppingAdapter adapter;
    private ShoppingHomeBean.ResultBean result;
    private ShoppingHomeDownBean.ResultBean down;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_home_shopping, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
        getDataFromNetToo();
    }

    private void getDataFromNetToo() {
        OkHttpUtils.get().url(AppNet.SHOPPINGHOMEDOWN).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {

                proceessDataDown(response);
            }
        });
    }

    private void proceessDataDown(String json) {
        ShoppingHomeDownBean shoppingHomeDownBean = JSON.parseObject(json, ShoppingHomeDownBean.class);
        down = shoppingHomeDownBean.getResult();
        initAdapter();

    }


    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNet.SHOPPINGHOME).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {

                proceessData(response);
            }
        });
    }

    private void proceessData(String json) {
        ShoppingHomeBean shoppingHomeBean = JSON.parseObject(json, ShoppingHomeBean.class);
//        Log.e("TAG", "解析成功 == 12312312312334543556765==" + shoppingHomeBean.getResult().getDescription());
        result = shoppingHomeBean.getResult();
        initAdapter();
    }
    private void initAdapter() {

        if (result != null && down != null) {
            //设置适配器
            adapter = new ShoppingAdapter(context, result,down);
            recyclerView.setAdapter(adapter);
            //布局管理器
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        }
    }
}
