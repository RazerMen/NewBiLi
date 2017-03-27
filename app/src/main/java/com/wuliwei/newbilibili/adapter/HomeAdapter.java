package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.activity.BannerWebActivity;
import com.wuliwei.newbilibili.bean.HomeBean;
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
 * 2017/3/22.
 * <p>
 * 作用：直播的分类型
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final HomeBean.DataBean datas;

    //横幅
    public static final int BANNER = 0;

    //绘画
    public static final int DRAWABLE = 1;

    //生活
    public static final int LEFT = 2;

    //歌舞
    public static final int SINGER = 3;

    //手游直播
    public static final int GAME = 4;

    //单机联机
    public static final int DANJI = 5;

    //网络游戏
    public static final int NETGAME = 6;

    //电子竞技
    public static final int DIANJING = 7;

    //御宅文化
    public static final int YUZHAI = 8;

    //放映厅
    public static final int SHOW = 9;

    //推荐主播

    //当前的类型
    public int currentType = BANNER;

    //布局加载器
    private final LayoutInflater inflater;

    private PaintingAdapter paintingAdapter;

    public HomeAdapter(Context context, HomeBean.DataBean data) {
        this.context = context;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == DRAWABLE) {
            currentType = DRAWABLE;
        } else if (position == LEFT) {
            currentType = LEFT;
        } else if (position == SINGER) {
            currentType = SINGER;
        } else if (position == GAME) {
            currentType = GAME;
        } else if (position == DANJI) {
            currentType = DANJI;
        } else if (position == NETGAME) {
            currentType = NETGAME;
        } else if (position == DIANJING) {
            currentType = DIANJING;
        } else if (position == YUZHAI) {
            currentType = YUZHAI;
        } else if (position == SHOW) {
            currentType = SHOW;
        }
        return currentType;

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_item, null));
        } else if (viewType == DRAWABLE) {
            return new DrawableViewHolder(context, inflater.inflate(R.layout.drawable_item, null));
        } else if (viewType == LEFT) {
            return new LiftViewHolder(context, inflater.inflate(R.layout.lift_item, null));
        } else if (viewType == SINGER) {
            return new SingerViewHolder(context, inflater.inflate(R.layout.singer_item, null));
        } else if (viewType == GAME) {
            return new GameViewHolder(context, inflater.inflate(R.layout.game_item, null));
        } else if (viewType == DANJI) {
            return new DanjiViewHolder(context, inflater.inflate(R.layout.danji_item, null));
        } else if (viewType == NETGAME) {
            return new NetgameViewHolder(context, inflater.inflate(R.layout.net_game_item, null));
        } else if (viewType == DIANJING) {
            return new DianJingViewHodler(context, inflater.inflate(R.layout.dian_jing_item, null));
        } else if (viewType == YUZHAI) {
            return new YuZhaiViewHodler(context, inflater.inflate(R.layout.yu_zhai_item, null));
        } else if (viewType == SHOW) {
            return new ShowViewHolder(context, inflater.inflate(R.layout.show_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(datas.getBanner());
        } else if (getItemViewType(position) == DRAWABLE) {
            DrawableViewHolder drawableHodleView = (DrawableViewHolder) holder;
            drawableHodleView.setData(datas);
        } else if (getItemViewType(position) == LEFT) {
            LiftViewHolder leftHodleView = (LiftViewHolder) holder;
            leftHodleView.setData(datas);
        } else if (getItemViewType(position) == SINGER) {
            SingerViewHolder singerViewHolder = (SingerViewHolder) holder;
            singerViewHolder.setData(datas);
        } else if (getItemViewType(position) == GAME) {
            GameViewHolder gameViewHolder = (GameViewHolder) holder;
            gameViewHolder.setData(datas);
        } else if (getItemViewType(position) == DANJI) {
            DanjiViewHolder danjiViewHolder = (DanjiViewHolder) holder;
            danjiViewHolder.setData(datas);
        } else if (getItemViewType(position) == NETGAME) {
            NetgameViewHolder netgameViewHolder = (NetgameViewHolder) holder;
            netgameViewHolder.setData(datas);
        } else if (getItemViewType(position) == DIANJING) {
            DianJingViewHodler dianJingViewHodler = (DianJingViewHodler) holder;
            dianJingViewHodler.setData(datas);
        } else if (getItemViewType(position) == YUZHAI) {
            YuZhaiViewHodler yuZhaiViewHolder = (YuZhaiViewHodler) holder;
            yuZhaiViewHolder.setData(datas);
        } else if (getItemViewType(position) == SHOW) {
            ShowViewHolder showViewHolder = (ShowViewHolder) holder;
            showViewHolder.setData(datas);
        }
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_show)
        MyGridView gvShow;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public ShowViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(8).getLives());
            gvShow.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(8).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class YuZhaiViewHodler extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_yuzhai)
        MyGridView gvYuZhai;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public YuZhaiViewHodler(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(7).getLives());
            gvYuZhai.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(7).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class DianJingViewHodler extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_dianjing)
        MyGridView gvDianJing;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public DianJingViewHodler(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(6).getLives());
            gvDianJing.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(6).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class NetgameViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_netgame)
        MyGridView gvNetGame;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public NetgameViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(5).getLives());
            gvNetGame.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(5).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class DanjiViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_danji)
        MyGridView gvDanji;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public DanjiViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(4).getLives());
            gvDanji.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(4).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_game)
        MyGridView gvGame;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public GameViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(3).getLives());
            gvGame.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(3).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class SingerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_singer)
        MyGridView gvSinger;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public SingerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(2).getLives());
            gvSinger.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(2).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class LiftViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.gv_left)
        MyGridView gvLeft;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public LiftViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(1).getLives());
            gvLeft.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean partition = datas.getPartitions().get(1).getPartition();

            Glide.with(context).load(partition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(partition.getName());
            tvGeshu.setText(partition.getCount() + "");
            tvShuaxin.setText(partition.getCount() + "条新动态，点击刷新！");

        }
    }

    class DrawableViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.gv_drawable)
        GridView gvDrawable;
        @BindView(R.id.iv_drawable)
        ImageView ivDrawable;
        @BindView(R.id.tv_drawable)
        TextView tvDrawable;
        @BindView(R.id.tv_geshu)
        TextView tvGeshu;
        @BindView(R.id.ll_drawable)
        LinearLayout llDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public DrawableViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void setData(HomeBean.DataBean datas) {
            //设置适配器
            paintingAdapter = new PaintingAdapter(context, datas.getPartitions().get(0).getLives());
            gvDrawable.setAdapter(paintingAdapter);

            HomeBean.DataBean.PartitionsBean.PartitionBean prePosition = datas.getPartitions().get(0).getPartition();

            Glide.with(context).load(prePosition.getSub_icon().getSrc()).into(ivDrawable);
            tvDrawable.setText(prePosition.getName());
            tvGeshu.setText(prePosition.getCount() + "");
            tvShuaxin.setText(prePosition.getCount() + "条新动态，点击刷新！");

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @BindView(R.id.banner)
        Banner banner;

        public BannerViewHolder(Context conntext, View itemView) {
            super(itemView);
            this.context = conntext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<HomeBean.DataBean.BannerBean> banner_info) {

            List<String> imgs = new ArrayList<>();
//            String title = banner_info.get(0).getTitle();

            for (int i = 0; i < banner_info.size(); i++) {
                imgs.add(banner_info.get(i).getImg());
            }

            banner.setImages(imgs).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load((String) path).crossFade().into(imageView);
                }
            });


            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置标题
//            String[] titles = new String[]{"对象？你尽管抢，抢到算我输"};
//            banner.setBannerTitles(Arrays.asList(title));

            //设置手动滑动
            banner.setViewPagerIsScroll(true);

            banner.start();

            //设置banner的点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(context, BannerWebActivity.class);
                    intent.putExtra("title", banner_info.get(position).getTitle());
                    intent.putExtra("link", banner_info.get(position).getLink());
                    context.startActivity(intent);
                }
            });
        }
    }
}
