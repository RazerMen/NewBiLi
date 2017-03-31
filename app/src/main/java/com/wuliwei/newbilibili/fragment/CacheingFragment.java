package com.wuliwei.newbilibili.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.listener.ResponseListener;
import com.wuliwei.newbilibili.uitls.RetrofitUtils;
import com.wuliwei.newbilibili.view.MyProgressBar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by
 * 武立伟
 * 2017/3/25.
 * <p>
 * 作用：缓存中界面
 */

public class CacheingFragment extends BaseFragment {

    @BindView(R.id.tv_seekbar)
    TextView tvSeekbar;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.activity_download_list)
    LinearLayout activityDownloadList;
    Unbinder unbinder;
    //同时下载最大的量
    private int maxDownloadNubmer;
    //当前下载的数量
    private int downloadNumber;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_huancuning, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        recyclerview.setAdapter(new Adapter());
        recyclerview.setLayoutManager(new LinearLayoutManager(context));

        initListener();
    }

    private void initListener() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbar.setText("同时下载数量  " + (progress + 1) + "  ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vh, parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setData();
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progresss)
        MyProgressBar progresss;
        @BindView(R.id.tv_progress)
        TextView tvProgress;
        @BindView(R.id.bt_download)
        Button btDownload;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            btDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (maxDownloadNubmer == 0) {//max=0 表示第一次下载赋值最大下载数量
                        //seekbar 拖动范围0~4 +1相当于 同时下载数量范围为1~5
                        maxDownloadNubmer = seekbar.getProgress() + 1;
                        Log.e("TAG", "ViewHolder onClick()" + maxDownloadNubmer);
                    }

                    if (downloadNumber >= maxDownloadNubmer) {
                        Toast.makeText(context, "同时下载数量过多，无法下载", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    downloadNumber++;
                    btDownload.setText("下载中...");
                    btDownload.setEnabled(false);

                    RetrofitUtils.getInstance().download(new File(context.getExternalFilesDir(null), getLayoutPosition() + ".apk"), new ResponseListener() {
                        @Override
                        public void onProgress(final long progress, final long total, boolean done) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    long l = progress * 100 / total;
                                    progresss.setProgress((int) l);

                                    String pro = (int) progress * 100 / (int) total + "%";
                                    String p = RetrofitUtils.formetFileSize(progress);
                                    String t = RetrofitUtils.formetFileSize(total);
                                    tvProgress.setText(p + " / " + t + "\t" + pro);
                                }
                            });
                        }

                        @Override
                        public void onResponse() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadNumber--;
                                    btDownload.setText("下载完成！");
                                }
                            });
                        }

                        @Override
                        public void onFailure(final String error) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadNumber--;
                                    btDownload.setText("下载错误！");
                                    btDownload.setEnabled(false);
                                    Log.e("TAG", "DownloadActivity onFailure()" + error);
                                }
                            });
                        }
                    });
                }
            });
        }
    }
}
