package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.activity.BannerWebActivity;
import com.wuliwei.newbilibili.activity.HuaTiActivity;
import com.wuliwei.newbilibili.bean.FQDownBean;
import com.wuliwei.newbilibili.bean.FQUPBean;
import com.wuliwei.newbilibili.view.MyGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * 武立伟
 * 2017/3/24.
 * <p>
 * 作用：分区的适配器
 */

public class FQAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<FQUPBean.DataBean> up;
    private final List<FQDownBean.DataBean> down;

    //gridView
    public static final int UP = 0;

    //有banner
    public static final int REGION = 1;

    //话题
    public static final int HUATI = 2;

    //活动
    public static final int ACTIVITY = 3;

    //当前的位置
    public int currentType = 0;

    //布局加载器
    private final LayoutInflater inflater;

    private AnimationAdapter animationAdapter;

    private FQDownBean.DataBean downBean;

    public FQAdapter(Context context, List<FQUPBean.DataBean> upData, List<FQDownBean.DataBean> downData) {
        this.mContext = context;
        this.up = upData;
        this.down = downData;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return down.size() + 1;
//        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0) {
            downBean = down.get(position - 1);
        }
        if (position == 0) {
            currentType = UP;
        } else if ("region".equals(downBean.getType())) {
            currentType = REGION;
        } else if ("topic".equals(downBean.getType())) {
            currentType = HUATI;
        }else if("activity".equals(downBean.getType())) {
            currentType = ACTIVITY;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == UP) {
            return new UPViewHolder(mContext, inflater.inflate(R.layout.up_item, null));
        } else if (viewType == REGION) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.animation_item, null));
        } else if (viewType == HUATI) {
            return new HuaTiViewHolder(mContext, inflater.inflate(R.layout.huati_item, null));
        } else if (viewType == ACTIVITY) {
            return new ActivityViewHolder(mContext, inflater.inflate(R.layout.activity_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == UP) {
            UPViewHolder upViewHolder = (UPViewHolder) holder;
            upViewHolder.setData(up);
        } else if (getItemViewType(position) == REGION) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(downBean);
        } else if (getItemViewType(position) == HUATI) {
            HuaTiViewHolder huaTiViewHolder = (HuaTiViewHolder) holder;
            huaTiViewHolder.setData(downBean);
        } else if (getItemViewType(position) == ACTIVITY) {
            ActivityViewHolder activityViewHolder = (ActivityViewHolder) holder;
            activityViewHolder.setData(downBean);
        }
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        private HDAdapter adapter;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.iv_tupian)
        ImageView imageView;

        public ActivityViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this,itemView);
        }

        public void setData(FQDownBean.DataBean downBean) {
            //设置适配器
            adapter = new HDAdapter(mContext, downBean);
            recyclerView.setAdapter(adapter);

            tvDonghua.setText(downBean.getTitle());

            String btn = downBean.getTitle();
            String substring = btn.substring(0, btn.length() - 1);
//            btnDrawable.setText("更多" + substring);

            for (int i = 0; i < up.size(); i++) {
                FQUPBean.DataBean dataBean = up.get(i);

                if (substring.equals(up.get(i).getName())) {
                    Glide.with(mContext).load(dataBean.getLogo()).into(imageView);
                }
            }

            //布局管理器
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            llHuati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "22222", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, HuaTiActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class HuaTiViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.banner)
        Banner banner;

        public HuaTiViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mContext = mContext;
        }

        public void setData(FQDownBean.DataBean downBean) {

            final List<FQDownBean.DataBean.BodyBean> body = downBean.getBody();

            List<String> imgs = new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                imgs.add(body.get(i).getCover());
            }

            banner.setImages(imgs).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context)
                            .load((String) path)
                            .crossFade().into(imageView);
                }
            });

            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

            //设置手动滑动
            banner.setViewPagerIsScroll(true);

            banner.start();

            //设置banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, BannerWebActivity.class);
                    intent.putExtra("title", body.get(position).getTitle());
                    intent.putExtra("link", body.get(position).getUri());
                    mContext.startActivity(intent);
                }
            });

            llHuati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HuaTiActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridView)
        MyGridView myGridView;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.iv_tupian)
        ImageView ivTupian;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.context = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(FQDownBean.DataBean downBean) {
            //设置适配器
            animationAdapter = new AnimationAdapter(context, downBean.getBody());
            myGridView.setAdapter(animationAdapter);

            tvDonghua.setText(downBean.getTitle());

            String btn = downBean.getTitle();
            String substring = btn.substring(0, btn.length() - 1);
            btnDrawable.setText("更多" + substring);

            for (int i = 0; i < up.size(); i++) {
                FQUPBean.DataBean dataBean = up.get(i);

                if (substring.equals(up.get(i).getName())) {
                    Glide.with(context).load(dataBean.getLogo()).into(ivTupian);
                }
            }

            final FQDownBean.DataBean.BannerBean bannerBean = FQAdapter.this.downBean.getBanner();

            if (bannerBean != null && bannerBean.getBottom().size() > 0) {

                banner.setVisibility(View.VISIBLE);
                List<String> imgs = new ArrayList<>();
                for (int i = 0; i < bannerBean.getBottom().size(); i++) {
                    imgs.add(bannerBean.getBottom().get(i).getImage());
                }

                banner.setImages(imgs).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load((String) path)
                                .crossFade().into(imageView);
                    }
                });

                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

                //设置手动滑动
                banner.setViewPagerIsScroll(true);

                banner.start();

                //设置banner的点击事件
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mContext, BannerWebActivity.class);
                        intent.putExtra("title", bannerBean.getBottom().get(position).getTitle());
                        intent.putExtra("link", bannerBean.getBottom().get(position).getUri());
                        mContext.startActivity(intent);
                    }
                });

            } else {
                banner.setVisibility(View.GONE);
            }

        }
    }

    class UPViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private UPGVAdapter upgvAdapter;

        @BindView(R.id.gridView)
        MyGridView gridView;

        public UPViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQUPBean.DataBean> up) {
            //设置适配器
            upgvAdapter = new UPGVAdapter(mContext, up);
            gridView.setAdapter(upgvAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "位置==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
