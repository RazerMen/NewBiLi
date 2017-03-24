package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.FQDownBean;

import java.util.List;

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
    private final List<FQDownBean.DataBean.BodyBean> datas;

    public HDAdapter(Context mContext, List<FQDownBean.DataBean.BodyBean> body) {
        this.mContext = mContext;
        this.datas = body;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_activity, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FQDownBean.DataBean.BodyBean bodyBean = datas.get(position);
        Glide.with(mContext).load(bodyBean.getCover()).crossFade().into(holder.ivGvDra);
        holder.tvName.setText(bodyBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }
    }

    private OnItemClickListener listener;
    /**
     * 点击item接口
     */
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    /**
     * 设置item的点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
