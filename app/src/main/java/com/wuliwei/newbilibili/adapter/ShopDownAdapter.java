package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.ShoppingHomeDownBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/27.
 * <p>
 * 作用：
 */

public class ShopDownAdapter extends BaseAdapter {
    private final Context mContext;
    private final ShoppingHomeDownBean.ResultBean datas;

    public ShopDownAdapter(Context mContext, ShoppingHomeDownBean.ResultBean down) {
        this.mContext = mContext;
        this.datas = down;
    }

    @Override
    public int getCount() {
        return datas.getRecords().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_shop_down, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ShoppingHomeDownBean.ResultBean.RecordsBean recordsBean = datas.getRecords().get(position);

        Glide.with(mContext).load(recordsBean.getImgUrl()).into(viewHolder.ivGvDra);
        viewHolder.tvGvTit.setText(recordsBean.getTitle());
        viewHolder.tvMoney.setText( "￥" + recordsBean.getSalvePrice());
        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "位置==" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_gv_dra)
        ImageView ivGvDra;
        @BindView(R.id.tv_gv_tit)
        TextView tvGvTit;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
