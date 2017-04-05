package com.wuliwei.newbilibili.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.app.MyApplication;
import com.wuliwei.newbilibili.bean.GoodsBean;
import com.wuliwei.newbilibili.view.MyView.AddSubView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/28.
 * <p>
 * 作用：
 */

public class ShoppingCartAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
//    private int number = 1;

    public static final boolean isChecked = true;

    public ShoppingCartAdapter(Context context, List<GoodsBean> goodsBeen, TextView tvShopcartTotal, CheckBox checkboxAll) {
        this.mContext = context;
        this.datas = goodsBeen;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;

        showTotalPrice();
    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("" + getTotalPrice());
    }

    private double getTotalPrice() {
        double totalPrice = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                totalPrice += Double.parseDouble(goodsBean.getPrice()) * goodsBean.getNumber();
            }
        }
        return totalPrice;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_shop_car, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final GoodsBean goodsBean = datas.get(position);

//        viewHolder.cbGov.setChecked(isChecked);

        Glide.with(mContext).load(goodsBean.getImg()).into(viewHolder.ivGov);
        viewHolder.cbGov.setChecked(isChecked);
        viewHolder.tvDescGov.setText(goodsBean.getTitle());
        viewHolder.tvPriceGov.setText("￥" + goodsBean.getPrice());

        viewHolder.addSubView.setValue(goodsBean.getNumber());
        viewHolder.addSubView.setMinValue(1);
        viewHolder.addSubView.setMaxValue(10);

        viewHolder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                goodsBean.setNumber(value);

                MyApplication.getInstances().getDaoSession().update(goodsBean);

                //显示总价格
                showTotalPrice();

            }
        });

        return convertView;
    }

    //删除
    public void setData(List<GoodsBean> goodsBeen) {
        if (datas != null && datas.size() > 0) {
            datas.clear();
            datas.addAll(goodsBeen);
        }
    }

    public void setAdd(List<GoodsBean> goodsBeen) {
        if(datas != null && datas.size() > 0) {
            datas.clear();
            datas.addAll(goodsBeen);
        }
    }

    class ViewHolder {
        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.addSubView)
        AddSubView addSubView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
