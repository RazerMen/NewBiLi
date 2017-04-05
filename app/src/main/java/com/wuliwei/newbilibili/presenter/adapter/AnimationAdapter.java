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
import com.wuliwei.newbilibili.bean.FQDownBean;
import com.wuliwei.newbilibili.uitls.video.DanmkuVideoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用：分区动画的适配器
 */

public class AnimationAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<FQDownBean.DataBean.BodyBean> datas;

    public AnimationAdapter(Context mContext, List<FQDownBean.DataBean.BodyBean> body) {
        this.mContext = mContext;
        this.datas = body;
    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = View.inflate(mContext, R.layout.item_animation, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FQDownBean.DataBean.BodyBean bodyBean = datas.get(position);

        Glide.with(mContext).load(bodyBean.getCover()).crossFade().into(viewHolder.ivGvDra);
        viewHolder.tvGvDra.setText(bodyBean.getDanmaku() + "");
        viewHolder.tvName.setText(bodyBean.getPlay() + "");
        viewHolder.tvTitles.setText(bodyBean.getTitle());
        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playurl = datas.get(position).getUri();
                Intent intent = new Intent(mContext, DanmkuVideoActivity.class);
                intent.putExtra("link", playurl);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_gv_dra)
        ImageView ivGvDra;
        @BindView(R.id.tv_titles)
        TextView tvTitles;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_gv_dra)
        TextView tvGvDra;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
