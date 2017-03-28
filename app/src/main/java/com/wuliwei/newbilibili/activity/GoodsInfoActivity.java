package com.wuliwei.newbilibili.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.ShopDownAdapter;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.bean.GoodsBean;
import com.wuliwei.newbilibili.view.AddSubView;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsInfoActivity extends BaseActivity {

    @BindView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @BindView(R.id.iv_tupian)
    ImageView ivTupian;
    @BindView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @BindView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @BindView(R.id.addSubView)
    AddSubView addSubView;
    @BindView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;

    private GoodsBean goodsBean;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        goodsBean = (GoodsBean) intent.getSerializableExtra(ShopDownAdapter.GOODS_BEAN);
        setData();
    }

    private void setData() {
        Glide.with(this).load(goodsBean.getImg()).placeholder(R.drawable.aa).into(ivTupian);
        tvGoodInfoName.setText(goodsBean.getTitle());
        tvGoodInfoPrice.setText("￥" + goodsBean.getPrice());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_info;
    }

    @OnClick({R.id.ib_good_info_back, R.id.btn_good_info_addcart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.btn_good_info_addcart:
                Toast.makeText(GoodsInfoActivity.this, "成功加入到购物车", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
