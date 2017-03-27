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
import com.wuliwei.newbilibili.bean.JFBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用：番剧的适配器
 */

public class FanJuAdapter extends BaseAdapter {
    private final Context context;
    private final List<JFBean.ResultBean.AdBean.HeadBean> datas;

    public FanJuAdapter(Context context, List<JFBean.ResultBean.AdBean.HeadBean> head) {
        this.context = context;
        this.datas = head;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_fanju, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        JFBean.ResultBean.AdBean.HeadBean headBean = datas.get(position);
        Glide.with(context).load(headBean.getImg()).crossFade().into(viewHolder.ivGvDra);
        viewHolder.tvTitle.setText(headBean.getTitle());

        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "111", Toast.LENGTH_SHORT).show();
            }
        });

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
