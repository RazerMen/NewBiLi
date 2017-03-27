package com.wuliwei.newbilibili.adapter;

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
import com.wuliwei.newbilibili.bean.HomeBean;
import com.wuliwei.newbilibili.view.video.DanmkuVideoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/22.
 * <p>
 * 作用：会话的适配器
 */

public class PaintingAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.DataBean.PartitionsBean.LivesBean> datas;
    private HomeBean.DataBean.PartitionsBean.LivesBean livesBean;

    public PaintingAdapter(Context context, List<HomeBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.mContext = context;
        this.datas = lives;
    }

    @Override
    public int getCount() {
        return 6;
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
            convertView = View.inflate(mContext, R.layout.item_painting, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        livesBean = datas.get(position);
        Glide.with(mContext).load(livesBean.getCover().getSrc()).crossFade().into(viewHolder.ivGvDra);
        viewHolder.tvGvTit.setText(livesBean.getTitle());
        viewHolder.tvName.setText(livesBean.getOwner().getName());
        viewHolder.tvGvDra.setText(String.valueOf(livesBean.getOnline()));

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.item_live_layout)
        CardView cardView;
        @BindView(R.id.iv_gv_dra)
        ImageView ivGvDra;
        @BindView(R.id.tv_gv_tit)
        TextView tvGvTit;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_gv_dra)
        TextView tvGvDra;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DanmkuVideoActivity.class);
                    intent.putExtra("link",livesBean.getPlayurl());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
