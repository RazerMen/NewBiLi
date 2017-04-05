package com.wuliwei.newbilibili.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.presenter.adapter.ZhongHeAdapter;
import com.wuliwei.newbilibili.view.base.BaseFragment;
import com.wuliwei.newbilibili.bean.SearchBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by
 * 武立伟
 * 2017/3/28.
 * <p>
 * 作用：
 */

public class ZhongHeFragment extends BaseFragment {
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
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initSearch();
        getDataFromNet();

    }

    private void initSearch() {
        link = getActivity().getIntent().getStringExtra("link");
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(link).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("TAG", "请求成功搜索123123321321==" + response);
                proceessData(response);
            }
        });
    }

    private void proceessData(String json) {
        SearchBean searchBean = JSON.parseObject(json, SearchBean.class);
        Log.e("TAG", "解析成功==" + searchBean.getData().getItems().getArchive().get(0).getTitle());

        List<SearchBean.DataBean.ItemsBean.ArchiveBean> archive = searchBean.getData().getItems().getArchive();
        if (archive != null && archive.size() > 0) {
            //设置适配器
            adapter = new ZhongHeAdapter(context, archive);
            listView.setAdapter(adapter);
        }
    }
}
