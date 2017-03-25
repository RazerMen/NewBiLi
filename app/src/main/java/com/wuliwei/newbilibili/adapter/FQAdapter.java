package com.wuliwei.newbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    //动画区
    public static final int ANIMATION = 1;

    //国创区
    public static final int GUOCHUANG = 2;

    //音乐区
    public static final int MUSIC = 3;

    //话题
    public static final int HUATI = 4;

    //舞蹈
    public static final int DANCE = 5;

    //游戏
    public static final int GAME = 6;

    //话题2
    public static final int HUATI2 = 7;

    //鬼畜
    public static final int GUICHU = 8;

    //话题3
    public static final int HUATI3 = 9;

    //生活
    public static final int LEFT = 10;

    //话题4
    public static final int HUATI4 = 11;

    //科技
    public static final int KEJI = 12;

    //活动
//    public static final int ACTIVITY = 13;

    //话题5
    public static final int HUATI5 = 13;

    //时尚
    public static final int SHISHANG = 14;

    //广告
    public static final int GUANGGAO = 15;

    //娱乐
    public static final int PLAY = 16;

    //电视剧
    public static final int TV = 17;

    //话题6
    public static final int HUATI6 = 18;

    //电影
    public static final int MOVE = 19;

    //当前的位置
    public int currentType = UP;

    //布局加载器
    private final LayoutInflater inflater;


    private List<FQDownBean.DataBean.BannerBean.BottomBean> bottom;

    private AnimationAdapter animationAdapter;


    public FQAdapter(Context context, List<FQUPBean.DataBean> upData, List<FQDownBean.DataBean> downData) {
        this.mContext = context;
        this.up = upData;
        this.down = downData;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == UP) {
            currentType = UP;
        } else if (position == ANIMATION) {
            currentType = ANIMATION;
        } else if (position == GUOCHUANG) {
            currentType = GUOCHUANG;
        } else if (position == MUSIC) {
            currentType = MUSIC;
        } else if (position == HUATI) {
            currentType = HUATI;
        } else if (position == DANCE) {
            currentType = DANCE;
        } else if (position == GAME) {
            currentType = GAME;
        } else if (position == HUATI2) {
            currentType = HUATI2;
        } else if (position == GUICHU) {
            currentType = GUICHU;
        } else if (position == LEFT) {
            currentType = LEFT;
        } else if (position == HUATI3) {
            currentType = HUATI3;
        } else if (position == KEJI) {
            currentType = KEJI;
        } else if (position == HUATI4) {
            currentType = HUATI4;
        } else if (position == HUATI5) {
            currentType = HUATI5;
        } else if (position == SHISHANG) {
            currentType = SHISHANG;
        } else if (position == GUANGGAO) {
            currentType = GUANGGAO;
        }else if(position == PLAY) {
            currentType = PLAY;
        }else if(position == TV) {
            currentType = TV;
        }else if(position == HUATI6) {
            currentType = HUATI6;
        }else if(position == MOVE) {
            currentType = MOVE;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == UP) {
            return new UPViewHolder(mContext, inflater.inflate(R.layout.up_item, null));
        } else if (viewType == ANIMATION) {
            return new AnimationViewHolder(mContext, inflater.inflate(R.layout.animation_item, null));
        } else if (viewType == GUOCHUANG) {
            return new GuoChuangViewHolder(mContext, inflater.inflate(R.layout.guochuang_item, null));
        } else if (viewType == MUSIC) {
            return new MusicViewHolder(mContext, inflater.inflate(R.layout.music_item, null));
        } else if (viewType == HUATI) {
            return new HuaTiViewHolder(mContext, inflater.inflate(R.layout.huati_item, null));
        } else if (viewType == DANCE) {
            return new DanceViewHolder(mContext, inflater.inflate(R.layout.dance_item, null));
        } else if (viewType == GAME) {
            return new GameViewHolder(mContext, inflater.inflate(R.layout.games_item, null));
        } else if (viewType == GUICHU) {
            return new GuiChuViewHolder(mContext, inflater.inflate(R.layout.guichu_item, null));
        } else if (viewType == HUATI2) {
            return new HuaTiViewHolder2(mContext, inflater.inflate(R.layout.huati2_item, null));
        } else if (viewType == HUATI3) {
            return new HuaTiViewHolder3(mContext, inflater.inflate(R.layout.huati2_item, null));
        } else if (viewType == LEFT) {
            return new LeftViewHolder(mContext, inflater.inflate(R.layout.left_item, null));
        } else if (viewType == HUATI4) {
            return new HuaTiViewHolder4(mContext, inflater.inflate(R.layout.huati2_item, null));
        } else if (viewType == KEJI) {
            return new KeJiViewHolder(mContext, inflater.inflate(R.layout.keji_item, null));
        } else if (viewType == HUATI5) {
            return new HuaTiViewHolder5(mContext, inflater.inflate(R.layout.huati2_item, null));
        } else if (viewType == SHISHANG) {
            return new ShiShangViewHolder(mContext, inflater.inflate(R.layout.shishang_item, null));
        } else if (viewType == GUANGGAO) {
            return new GuangGaoViewHolder(mContext, inflater.inflate(R.layout.shishang_item, null));
        }else if(viewType == PLAY) {
            return new PlayViewHolder(mContext, inflater.inflate(R.layout.shishang_item, null));
        }else if(viewType == TV) {
            return new TvViewHolder(mContext, inflater.inflate(R.layout.shishang_item, null));
        }else if(viewType == HUATI6) {
            return new HuaTiViewHolder6(mContext, inflater.inflate(R.layout.huati2_item, null));
        }else if(viewType == MOVE) {
            return new MoveViewHolder(mContext, inflater.inflate(R.layout.shishang_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == UP) {
            UPViewHolder upViewHolder = (UPViewHolder) holder;
            upViewHolder.setData(up);
        } else if (getItemViewType(position) == ANIMATION) {
            AnimationViewHolder animationViewHolder = (AnimationViewHolder) holder;
            animationViewHolder.setData(down);
        } else if (getItemViewType(position) == GUOCHUANG) {
            GuoChuangViewHolder guoChuangViewHolder = (GuoChuangViewHolder) holder;
            guoChuangViewHolder.setData(down);
        } else if (getItemViewType(position) == MUSIC) {
            MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
            musicViewHolder.setData(down);
        } else if (getItemViewType(position) == HUATI) {
            HuaTiViewHolder huaTiViewHolder = (HuaTiViewHolder) holder;
            huaTiViewHolder.setData(down);
        } else if (getItemViewType(position) == DANCE) {
            DanceViewHolder danceViewHolder = (DanceViewHolder) holder;
            danceViewHolder.setData(down);
        } else if (getItemViewType(position) == GAME) {
            GameViewHolder gameViewHolder = (GameViewHolder) holder;
            gameViewHolder.setData(down);
        } else if (getItemViewType(position) == GUICHU) {
            GuiChuViewHolder guiChuViewHolder = (GuiChuViewHolder) holder;
            guiChuViewHolder.setData(down);
        } else if (getItemViewType(position) == HUATI2) {
            HuaTiViewHolder2 huaTiViewHolder2 = (HuaTiViewHolder2) holder;
            huaTiViewHolder2.setData(down);
        } else if (getItemViewType(position) == HUATI3) {
            HuaTiViewHolder3 huaTiViewHolder3 = (HuaTiViewHolder3) holder;
            huaTiViewHolder3.setData(down);
        } else if (getItemViewType(position) == LEFT) {
            LeftViewHolder leftViewHolder = (LeftViewHolder) holder;
            leftViewHolder.setData(down);
        } else if (getItemViewType(position) == HUATI4) {
            HuaTiViewHolder4 huaTiViewHolder4 = (HuaTiViewHolder4) holder;
            huaTiViewHolder4.setData(down);
        } else if (getItemViewType(position) == KEJI) {
            KeJiViewHolder keJiViewHolder = (KeJiViewHolder) holder;
            keJiViewHolder.setData(down);
        } else if (getItemViewType(position) == HUATI5) {
            HuaTiViewHolder5 huaTiViewHolder5 = (HuaTiViewHolder5) holder;
            huaTiViewHolder5.setData(down);
        } else if (getItemViewType(position) == SHISHANG) {
            ShiShangViewHolder shiShangViewHolder = (ShiShangViewHolder) holder;
            shiShangViewHolder.setData(down);
        }else if(getItemViewType(position) == GUANGGAO) {
            GuangGaoViewHolder guangGaoViewHolder = (GuangGaoViewHolder) holder;
            guangGaoViewHolder.setData(down);
        }else if(getItemViewType(position) == PLAY) {
            PlayViewHolder playViewHolder = (PlayViewHolder) holder;
            playViewHolder.setData(down);
        }else if(getItemViewType(position) == TV) {
            TvViewHolder tvViewHolder = (TvViewHolder) holder;
            tvViewHolder.setData(down);
        }else if(getItemViewType(position) == HUATI6) {
            HuaTiViewHolder6 huaTiViewHolder6 = (HuaTiViewHolder6) holder;
            huaTiViewHolder6.setData(down);
        }else if(getItemViewType(position) == MOVE) {
            MoveViewHolder moveViewHolder = (MoveViewHolder) holder;
            moveViewHolder.setData(down);
        }
    }

    class MoveViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public MoveViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(19).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(19).getTitle());
        }
    }

    class HuaTiViewHolder6 extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.banner)
        Banner banner;

        public HuaTiViewHolder6(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            List<String> imgs = new ArrayList<>();

//            bottom = down.get(6).getBanner().getBottom();

            String cover = down.get(18).getBody().get(0).getCover();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(cover);
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", bottom.get(position).getUri());
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

    class TvViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public TvViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(17).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(17).getTitle());
        }
    }

    class PlayViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public PlayViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(16).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(16).getTitle());
        }
    }

    class GuangGaoViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public GuangGaoViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(15).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(15).getTitle());
        }
    }

    class ShiShangViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public ShiShangViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(14).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(14).getTitle());
        }
    }

    class HuaTiViewHolder5 extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.banner)
        Banner banner;

        public HuaTiViewHolder5(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            List<String> imgs = new ArrayList<>();

//            bottom = down.get(6).getBanner().getBottom();

            String cover = down.get(13).getBody().get(0).getCover();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(cover);
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", bottom.get(position).getUri());
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

    class KeJiViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public KeJiViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(11).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(11).getTitle());
        }
    }

    class HuaTiViewHolder4 extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.banner)
        Banner banner;

        public HuaTiViewHolder4(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<FQDownBean.DataBean> down) {
            List<String> imgs = new ArrayList<>();

//            bottom = down.get(6).getBanner().getBottom();

            String cover = down.get(10).getBody().get(0).getCover();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(cover);
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", down.get(position).getBody().get(position).getUri());
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

    class LeftViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public LeftViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(9).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(9).getTitle());
        }
    }

    class HuaTiViewHolder3 extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.banner)
        Banner banner;

        public HuaTiViewHolder3(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            List<String> imgs = new ArrayList<>();

//            bottom = down.get(6).getBanner().getBottom();

            String cover = down.get(8).getBody().get(0).getCover();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(cover);
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", bottom.get(position).getUri());
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

    class GuiChuViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public GuiChuViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(7).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(7).getTitle());
        }
    }

    class HuaTiViewHolder2 extends RecyclerView.ViewHolder {

        private final Context mContext;
        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.banner)
        Banner banner;

        public HuaTiViewHolder2(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            List<String> imgs = new ArrayList<>();

//            bottom = down.get(6).getBanner().getBottom();

            String cover = down.get(6).getBody().get(0).getCover();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(cover);
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", bottom.get(position).getUri());
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

    class GameViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public GameViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(5).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(5).getTitle());
        }
    }

    class DanceViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public DanceViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(4).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(4).getTitle());
        }
    }

    class HuaTiViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.ll_huati)
        LinearLayout llHuati;
        @BindView(R.id.iv_huati)
        ImageView ivHuati;

        public HuaTiViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {

//            tvDonghua.setText(down.get(3).getTitle());
            Glide.with(mContext).load(down.get(3).getBody().get(0).getCover()).into(ivHuati);

            ivHuati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BannerWebActivity.class);
                    intent.putExtra("title", bottom.get(0).getTitle());
                    intent.putExtra("link", bottom.get(0).getUri());
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

    class MusicViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;
        @BindView(R.id.banner)
        Banner banner;

        public MusicViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(2).getBody());
            myGridViewGC.setAdapter(animationAdapter);


            tvDonghua.setText(down.get(2).getTitle());

            List<String> imgs = new ArrayList<>();

            bottom = down.get(2).getBanner().getBottom();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(bottom.get(i).getImage());
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", bottom.get(position).getUri());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class GuoChuangViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridViewGC)
        MyGridView myGridViewGC;
        @BindView(R.id.btn_drawable)
        Button btnDrawable;
        @BindView(R.id.tv_shuaxin)
        TextView tvShuaxin;

        public GuoChuangViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(1).getBody());
            myGridViewGC.setAdapter(animationAdapter);

            tvDonghua.setText(down.get(1).getTitle());
        }
    }

    class AnimationViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @BindView(R.id.tv_donghua)
        TextView tvDonghua;
        @BindView(R.id.btn_come)
        Button btnCome;
        @BindView(R.id.myGridView)
        MyGridView myGridView;
        @BindView(R.id.banner)
        Banner banner;

        public AnimationViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<FQDownBean.DataBean> down) {
            //设置适配器
            animationAdapter = new AnimationAdapter(mContext, down.get(0).getBody());
            myGridView.setAdapter(animationAdapter);


            tvDonghua.setText(down.get(0).getTitle());

            List<String> imgs = new ArrayList<>();

            bottom = down.get(0).getBanner().getBottom();

            for (int i = 0; i < bottom.size(); i++) {
                imgs.add(bottom.get(i).getImage());
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
                    intent.putExtra("title", bottom.get(position).getTitle());
                    intent.putExtra("link", bottom.get(position).getUri());
                    mContext.startActivity(intent);
                }
            });
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
        }
    }
}
