package com.wuliwei.newbilibili.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.GoodsBeanDao;
import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.ShopDownAdapter;
import com.wuliwei.newbilibili.app.MyApplication;
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
    private int number = 1;
    private GoodsBeanDao goodsBeanDao;

    @Override
    protected void initListener() {

        addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                number = value;
            }
        });
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        goodsBean = (GoodsBean) intent.getSerializableExtra(ShopDownAdapter.GOODS_BEAN);
        goodsBeanDao = MyApplication.getInstances().getDaoSession().getGoodsBeanDao();
        setData();
    }

    private void setData() {
        Glide.with(this).load(goodsBean.getImg()).placeholder(R.drawable.aa).into(ivTupian);
        tvGoodInfoName.setText(goodsBean.getTitle());
        tvGoodInfoPrice.setText("￥" + goodsBean.getPrice());

        addSubView.setMinValue(1);
        addSubView.setMaxValue(10);

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
                Toast.makeText(GoodsInfoActivity.this, "成功加入到购物车" + number, Toast.LENGTH_SHORT).show();

                Long id = goodsBean.getId();
                String title = goodsBean.getTitle();
                String price = goodsBean.getPrice() +  "";
                String img = goodsBean.getImg();

                goodsBean = new GoodsBean(null, title, price, img, number);

                goodsBeanDao.insert(goodsBean);
//
//                List<GoodsBean> goodsBeen = goodsBeanDao.loadAll();
//                String name = "";
//                for(int i = 0; i < goodsBeen.size(); i++) {
//                  name += goodsBeen.get(i).getTitle()+"    "+ goodsBeen.get(i).getId() +"    ";
//                }
//                Log.e("TAG", "GoodsInfoActivity onClick()" + name);
                break;
        }
    }
}
