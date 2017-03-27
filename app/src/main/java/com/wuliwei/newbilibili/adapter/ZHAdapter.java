package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.ZHBean;
import com.wuliwei.newbilibili.view.video.DanmkuVideoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/23.
 * <p>
 * 作用：综合的适配器
 */

public class ZHAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ZHBean.DataBean> datas;

    public ZHAdapter(Context context, List<ZHBean.DataBean> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_zh, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ZHBean.DataBean dataBean = datas.get(position);

        Glide.with(mContext).load(dataBean.getCover()).crossFade().into(viewHolder.ivGvDra);
        viewHolder.tvName.setText(dataBean.getTname());
        viewHolder.tvTiao.setText(dataBean.getTid() + "");
        viewHolder.tvTitle.setText(dataBean.getTitle());
        viewHolder.tvZh.setText(dataBean.getPlay() + "");

        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = dataBean.getUri();
//                String uri = datas.get(position).getUri();
                Intent intent = new Intent(mContext, DanmkuVideoActivity.class);
                intent.putExtra("link",uri);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_gv_dra)
        ImageView ivGvDra;
        @BindView(R.id.tv_zh)
        TextView tvZh;
        @BindView(R.id.tv_tiao)
        TextView tvTiao;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll2)
        LinearLayout ll2;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.rl_zh)
        RelativeLayout rlZh;
        @BindView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
