package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.JFBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用：国漫的适配器
 */

public class GMAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<JFBean.ResultBean.SerializingBean> datas;

    public GMAdapter(Context mContext, List<JFBean.ResultBean.SerializingBean> serializing) {
        this.mContext = mContext;
        this.datas = serializing;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = View.inflate(mContext, R.layout.item_fanju, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        JFBean.ResultBean.SerializingBean serializingBean = datas.get(position);
        Glide.with(mContext).load(serializingBean.getCover()).crossFade().into(viewHolder.ivGvDra);
        viewHolder.tvTitle.setText(serializingBean.getTitle());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_gv_dra)
        ImageView ivGvDra;
        @BindView(R.id.gv_geshu)
        TextView gvGeshu;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_gengxin)
        TextView tvGengxin;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
