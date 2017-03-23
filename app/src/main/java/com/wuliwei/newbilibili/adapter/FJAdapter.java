package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.FJBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：原创的适配器
 */

public class FJAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<FJBean.DataBean> datas;

    public FJAdapter(Context context, List<FJBean.DataBean> data) {
        this.mContext = context;
        this.datas = data;
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
            convertView = View.inflate(mContext, R.layout.item_yc, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FJBean.DataBean dataBean = datas.get(position);

        Glide.with(mContext).load(dataBean.getCover()).into(viewHolder.ivYc);
        viewHolder.tvTitles.setText(dataBean.getTitle());
        viewHolder.tvName.setText(dataBean.getName());
        viewHolder.tvFen.setText("综合评分：" + dataBean.getPts());

        if (position == 0) {
            viewHolder.tvNum.setTextColor(Color.parseColor("#FB7299"));
            viewHolder.tvNum.setText("" + (position + 1));
            viewHolder.tvNum.setTextSize(34);
        } else if (position == 1) {
            viewHolder.tvNum.setTextColor(Color.parseColor("#FB7299"));
            viewHolder.tvNum.setText("" + (position + 1));
            viewHolder.tvNum.setTextSize(30);
        } else if (position == 2) {
            viewHolder.tvNum.setTextColor(Color.parseColor("#FB7299"));
            viewHolder.tvNum.setText("" + (position + 1));
            viewHolder.tvNum.setTextSize(26);
        } else {
            viewHolder.tvNum.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.tvNum.setText("" + (position + 1));
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.iv_yc)
        ImageView ivYc;
        @BindView(R.id.tv_titles)
        TextView tvTitles;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_fen)
        TextView tvFen;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
