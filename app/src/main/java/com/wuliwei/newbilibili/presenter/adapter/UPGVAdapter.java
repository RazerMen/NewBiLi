package com.wuliwei.newbilibili.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.FQUPBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用：分区gridView的适配器
 */

public class UPGVAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<FQUPBean.DataBean> datas;

    public UPGVAdapter(Context mContext, List<FQUPBean.DataBean> up) {
        this.mContext = mContext;
        this.datas = up;
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
            convertView = View.inflate(mContext, R.layout.item_up, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FQUPBean.DataBean dataBean = datas.get(position);
        Glide.with(mContext).load(dataBean.getLogo()).crossFade().into(viewHolder.ivZhibo);
        viewHolder.tvZhibo.setText(dataBean.getName());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_zhibo)
        ImageView ivZhibo;
        @BindView(R.id.tv_zhibo)
        TextView tvZhibo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
