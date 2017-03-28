package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.activity.BannerWebActivity;
import com.wuliwei.newbilibili.bean.ShoppingHomeBean;
import com.wuliwei.newbilibili.bean.ShoppingHomeDownBean;
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
 * 2017/3/27.
 * <p>
 * 作用：
 */

public class ShoppingAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final ShoppingHomeBean.ResultBean datas;
    private final ShoppingHomeDownBean.ResultBean down;

    //横幅
    public static final int BANNER = 0;

    //商品
    public static final int SHOP = 1;

//    public static final int SHOPDOWN = 2;

    //当前的类型
    public int currentType = BANNER;

    //布局加载器
    private final LayoutInflater inflater;

    public ShoppingAdapter(Context context, ShoppingHomeBean.ResultBean result, ShoppingHomeDownBean.ResultBean down) {
        this.mContext = context;
        this.datas = result;
        this.down = down;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == SHOP) {
            currentType = SHOP;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.item_banner, null));
        } else if (viewType == SHOP) {
            return new ShopViewHolder(mContext, inflater.inflate(R.layout.item_shop, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(datas.getModelDetails());
        }else if(getItemViewType(position) == SHOP) {
            ShopViewHolder shopViewHolder = (ShopViewHolder) holder;
            shopViewHolder.setData(down);
        }
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private ShopDownAdapter adapter;

        @BindView(R.id.gv_danji)
        MyGridView gvDanji;

        public ShopViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this,itemView);
        }

        public void setData(ShoppingHomeDownBean.ResultBean down) {

            //设置适配器
            if(down != null && down.getRecords().size() > 0) {
                adapter = new ShopDownAdapter(mContext,down);
                gvDanji.setAdapter(adapter);
            }
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.banner)
        Banner banner;


        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<ShoppingHomeBean.ResultBean.ModelDetailsBean> modelDetails) {
            List<String> imgs = new ArrayList<>();

            for (int i = 0; i < modelDetails.size(); i++) {
                imgs.add(modelDetails.get(i).getSmallImageUrl());
            }

            banner.setImages(imgs).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load((String) path).crossFade().into(imageView);
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
                    intent.putExtra("title", modelDetails.get(position).getBigTitle());
                    intent.putExtra("link", modelDetails.get(position).getImgLink());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
