package com.wuliwei.newbilibili.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.github.hymanme.tagflowlayout.OnTagClickListener;
import com.github.hymanme.tagflowlayout.TagAdapter;
import com.github.hymanme.tagflowlayout.TagFlowLayout;
import com.github.hymanme.tagflowlayout.tags.ColorfulTagView;
import com.github.hymanme.tagflowlayout.tags.DefaultTagView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.activity.AllRegionActivity;
import com.wuliwei.newbilibili.activity.BannerWebActivity;
import com.wuliwei.newbilibili.activity.HuaTiActivity;
import com.wuliwei.newbilibili.activity.OriginalActivity;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.bean.FindBean;
import com.wuliwei.newbilibili.bean.ShopBean;
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 82390 on 2017/3/22.
 */

public class FindFragment extends BaseFragment {

    @BindView(R.id.tagFlowLayout)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.ll_xingqu)
    LinearLayout llXingqu;
    @BindView(R.id.ll_huati)
    LinearLayout llHuati;
    @BindView(R.id.ll_huodong)
    LinearLayout llHuodong;
    @BindView(R.id.ll_heiwu)
    LinearLayout llHeiwu;
    @BindView(R.id.ll_yuanchuang)
    LinearLayout llYuanchuang;
    @BindView(R.id.ll_quanqu)
    LinearLayout llQuanqu;
    @BindView(R.id.ll_game)
    LinearLayout llGame;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.iv_search)
    TextView ivSearch;
    @BindView(R.id.ib_sao)
    ImageButton ibSao;

    private List<FindBean.DataBean.ListBean> list;
    private ShopBean.ResultBean result;
    private SearchFragment searchFragment;
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_find, null);
        ButterKnife.bind(this, view);
        searchFragment = SearchFragment.newInstance();
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(AppNet.FIND_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                proceessData(response);
            }
        });
        OkHttpUtils.get().url(AppNet.SHOP_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                okhttp(response);
            }
        });
    }

    private void proceessData(String json) {
        FindBean findBean = JSON.parseObject(json, FindBean.class);

        list = findBean.getData().getList();

        Log.e("TAG", "111111111==" + list);

        initColor();

        //设置适配器
        //设置adapter
        MyTagAdapter tagAdapter = new MyTagAdapter();
        tagFlowLayout.setTagAdapter(tagAdapter);


        //给adapter绑定数据
        tagAdapter.addAllTags(list);

        initListener();
    }


    private void okhttp(String json) {
        ShopBean shopBean = JSON.parseObject(json, ShopBean.class);
        result = shopBean.getResult();
        Log.e("TAG", "222222222" + result);
    }

    private void initListener() {
        //设置监听(单击和长按事件)
        tagFlowLayout.setTagListener(new OnTagClickListener() {
            @Override
            public void onClick(TagFlowLayout parent, View view, int position) {
                Toast.makeText(context, "click==" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(TagFlowLayout parent, View view, int position) {
                Toast.makeText(context, "long click==" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initColor() {
        tagFlowLayout.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tagFlowLayout.setTitleTextSize(14);
        //最小显示高度(单位dp)
        tagFlowLayout.setMinVisibleHeight(100);
        //最大显示高度(单位dp)
        tagFlowLayout.setMaxVisibleHeight(300);
        tagFlowLayout.setAnimationDuration(500);
        //设置背景颜色
        tagFlowLayout.setBackGroundColor(getResources().getColor(R.color.primary_text));
    }


    @OnClick({R.id.iv_search, R.id.ib_sao, R.id.ll_xingqu, R.id.ll_huati, R.id.ll_huodong, R.id.ll_heiwu, R.id.ll_yuanchuang, R.id.ll_quanqu, R.id.ll_game, R.id.ll_shop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
//                Toast.makeText(ToolBarActivity.this, keyword, Toast.LENGTH_SHORT).show();
                    }
                });
                searchFragment.show(getFragmentManager(), SearchFragment.TAG);
                break;
            case R.id.ib_sao:
//                Toast.makeText(context, "扫描", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.ll_xingqu:
                Toast.makeText(context, "兴趣", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_huati:
//                Toast.makeText(context, "话题", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, HuaTiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_huodong:
//                Toast.makeText(context, "活动", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, HuaTiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_heiwu:
                Toast.makeText(context, "小黑屋", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_yuanchuang:
//                Toast.makeText(context, "原创", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, OriginalActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_quanqu:
//                Toast.makeText(context, "全区", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, AllRegionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_game:
                Toast.makeText(context, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_shop:
//                Toast.makeText(context, "商城", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, BannerWebActivity.class);
                intent.putExtra("title", result.getDescription());
                intent.putExtra("link", result.getResourceLink());
                context.startActivity(intent);
                break;
        }
    }

    //自定义Adapter：MyTagAdapter，其中TagBean为泛型类，即每一个tag的实体类
    //在getView()里面自定义tag标签的样式
    //默认提供了两个实例tag：DefaultTagView，ColorfulTagView
    //DefaultTagView：默认tag
    //ColorfulTagView：彩色的tag
    //当然还可以自己自定义
    class MyTagAdapter extends TagAdapter<FindBean.DataBean.ListBean> {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //定制tag的样式，包括背景颜色，点击时背景颜色，背景形状等
            DefaultTagView textView = new ColorfulTagView(context);
            textView.setText(list.get(position).getKeyword());
            return textView;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(context, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(context, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
