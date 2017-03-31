package com.wuliwei.newbilibili.fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseFragment;

import java.io.BufferedReader;
import java.io.FileReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by
 * 武立伟
 * 2017/3/25.
 * <p>
 * 作用：已缓存界面
 */

public class CacheFragment extends BaseFragment {
    @BindView(R.id.iv_huancun)
    ImageView ivHuancun;
    @BindView(R.id.tv_neicun)
    TextView tvNeicun;
    Unbinder unbinder;

    //获取手机当前可用内存
    private String getAvailMemory() {
        ActivityManager am = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
//        Formatter formatter = new Formatter();
//        formatter.formatFileSize(getContext(), mi.availMem);
        return Formatter.formatFileSize(getContext(), mi.availMem);// 将获取的内存大小规格;
    }

    private String getTotalMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

//        FileReader localFileReader = null;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("//s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "/t");
            }
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Formatter.formatFileSize(getContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_huancun, null);
        unbinder = ButterKnife.bind(this, view);
        tvNeicun.setText("主储存: " + getTotalMemory() + ", " + "可用: " + getAvailMemory());
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
