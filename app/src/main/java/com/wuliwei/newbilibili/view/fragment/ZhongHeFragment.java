package com.wuliwei.newbilibili.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.bean.SearchBean;
import com.wuliwei.newbilibili.presenter.adapter.ZhongHeAdapter;
import com.wuliwei.newbilibili.view.base.BaseFragment1;

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

public class ZhongHeFragment extends BaseFragment1 {
    @BindView(R.id.tv_paixu)
    TextView tvPaixu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_fenqu)
    TextView tvFenqu;
    @BindView(R.id.listView)
    ListView listView;
    private String link;
    private ZhongHeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_zonghe, null);
        ButterKnife.bind(this, view);
        initSearch();
        return view;
    }

    private void initSearch() {
        link = getActivity().getIntent().getStringExtra("link");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getUrl() {
        return link;
    }

    @Override
    protected void initData(String json, String error) {

        if (TextUtils.isEmpty(json)) {
            Log.e("TAG", "ZhongHeFragment initData()" + error);
        } else {
            SearchBean searchBean = JSON.parseObject(json, SearchBean.class);
            int code = searchBean.getCode();
            List<SearchBean.DataBean.ItemsBean.ArchiveBean> archive = searchBean.getData().getItems().getArchive();
            if (code == 0) {
                setAdapter(archive);
            } else {
                Log.e("TAG", "联网失败");
            }
        }
    }

    private void setAdapter(List<SearchBean.DataBean.ItemsBean.ArchiveBean> archive) {
        if (adapter == null) {
            adapter = new ZhongHeAdapter(context, archive);
        } else {
            adapter.notifyDataSetChanged();
        }
        //设置适配器
        listView.setAdapter(adapter);
    }
}
