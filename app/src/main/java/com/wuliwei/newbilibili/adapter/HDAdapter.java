package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.activity.BannerWebActivity;
import com.wuliwei.newbilibili.bean.FQDownBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用：分区里活动中心的适配器
 */

public class HDAdapter extends RecyclerView.Adapter<HDAdapter.ViewHolder> {
    private final Context mContext;
    private final FQDownBean.DataBean datas;

    public HDAdapter(Context mContext, FQDownBean.DataBean downBean) {
        this.mContext = mContext;
        this.datas = downBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_activity, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FQDownBean.DataBean.BodyBean bodyBean = datas.getBody().get(position);
        Glide.with(mContext).load(bodyBean.getCover()).into(holder.ivGvDra);
        holder.tvName.setText(bodyBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.getBody().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_gv_dra)
        ImageView ivGvDra;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemLiveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BannerWebActivity.class);
                    intent.putExtra("title", datas.getBody().get(getLayoutPosition()).getTitle());
                    intent.putExtra("link", datas.getBody().get(getLayoutPosition()).getUri());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
