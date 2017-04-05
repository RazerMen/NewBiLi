package com.wuliwei.newbilibili.presenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.view.activity.BannerWebActivity;
import com.wuliwei.newbilibili.bean.HuaTiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：话题的适配器
 */

public class HuaTiAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HuaTiBean.ListBean> datas;

    public HuaTiAdapter(Context mContext, List<HuaTiBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
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
            convertView = View.inflate(mContext, R.layout.item_huati, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final HuaTiBean.ListBean listBean = datas.get(position);

        Glide.with(mContext).load(listBean.getCover()).into(viewHolder.ivHuati);
        viewHolder.tvHuati.setText(listBean.getTitle());

        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BannerWebActivity.class);
                intent.putExtra("title", listBean.getTitle());
                intent.putExtra("link", listBean.getLink());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_huati)
        ImageView ivHuati;
        @BindView(R.id.tv_huati)
        TextView tvHuati;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
