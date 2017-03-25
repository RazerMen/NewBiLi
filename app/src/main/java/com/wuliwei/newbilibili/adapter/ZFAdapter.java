package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.activity.LoginActivity;
import com.wuliwei.newbilibili.bean.JFBean;
import com.wuliwei.newbilibili.view.MyGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用： 追番的适配器
 */

public class ZFAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final JFBean.ResultBean datas;

    //番剧
    public static final int FANJU = 0;

    //国漫
    public static final int GUOMAN = 1;

    //当前的
    public int currentType = FANJU;

    //设置布局加载器
    private final LayoutInflater inflater;

    private FanJuAdapter fanJuAdapter;

    public ZFAdapter(Context context, JFBean.ResultBean result) {
        this.mContext = context;
        this.datas = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == FANJU) {
            currentType = FANJU;
        } else if (position == GUOMAN) {
            currentType = GUOMAN;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FANJU) {
            return new FanJuViewHolder(mContext, inflater.inflate(R.layout.fanju_item, null));
        } else if (viewType == GUOMAN) {
            return new GuoManViewHolder(mContext, inflater.inflate(R.layout.huoman_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FANJU) {
            FanJuViewHolder fanJuViewHolder = (FanJuViewHolder) holder;
            fanJuViewHolder.setData(datas.getAd().getHead());
        } else if (getItemViewType(position) == GUOMAN) {
            GuoManViewHolder guoManViewHolder = (GuoManViewHolder) holder;
            guoManViewHolder.setData(datas.getSerializing());
        }
    }

    class GuoManViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private GMAdapter gmAdapter;

        @BindView(R.id.ll_guoman)
        LinearLayout llGuoman;
        @BindView(R.id.gridView)
        MyGridView gridView;

        public GuoManViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<JFBean.ResultBean.SerializingBean> serializing) {
            //设置适配器
            gmAdapter = new GMAdapter(mContext, serializing);
            gridView.setAdapter(gmAdapter);

        }
    }

    class FanJuViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.ll_fanju)
        LinearLayout llFanju;
        @BindView(R.id.gridView)
        MyGridView gridView;
        @BindView(R.id.iv_login)
        ImageView ivLogin;

        public FanJuViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.context = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<JFBean.ResultBean.AdBean.HeadBean> head) {
            //设置适配器
            fanJuAdapter = new FanJuAdapter(context, head);
            gridView.setAdapter(fanJuAdapter);

            ivLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
