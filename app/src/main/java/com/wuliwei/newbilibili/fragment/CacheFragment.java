package com.wuliwei.newbilibili.fragment;

import android.view.View;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseFragment;

/**
 * Created by
 * 武立伟
 * 2017/3/25.
 * <p>
 * 作用：已缓存界面
 */

public class CacheFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_huancun, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

//    /** 得到系统可用内存 **/
//    @SuppressLint("NewApi")
//    private String getMemFree(){
//        StatFs fs = new StatFs(Environment.getDataDirectory().getPath());
//        return Formatter.formatFileSize(context, (fs.getAvailableBytes()));
//    }

}
