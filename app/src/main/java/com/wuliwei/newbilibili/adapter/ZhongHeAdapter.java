package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.SearchBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/28.
 * <p>
 * 作用：
 */

public class ZhongHeAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<SearchBean.DataBean.ItemsBean.ArchiveBean> datas;

    public ZhongHeAdapter(Context context, List<SearchBean.DataBean.ItemsBean.ArchiveBean> archive) {
        this.mContext = context;
        this.datas = archive;
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
            convertView = View.inflate(mContext, R.layout.item_zhonghe, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SearchBean.DataBean.ItemsBean.ArchiveBean archiveBean = datas.get(position);

        Glide.with(mContext).load(archiveBean.getCover()).into(viewHolder.ivTupian);
        viewHolder.tvTime.setText(archiveBean.getDuration());
        viewHolder.tvTitle.setText(archiveBean.getTitle());
        viewHolder.tvName.setText(archiveBean.getAuthor());
        viewHolder.tvShuliang.setText(archiveBean.getPlay() + "");
        viewHolder.tvGeshu.setText(archiveBean.getDanmaku() + "");

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_tupian)
        ImageView ivTupian;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_shuliang)
        TextView tvShuliang;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
