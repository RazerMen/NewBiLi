package com.wuliwei.newbilibili.view.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.presenter.adapter.SearchAdapter;
import com.wuliwei.newbilibili.view.base.BaseActivity;
import com.wuliwei.newbilibili.view.base.BaseFragment;
import com.wuliwei.newbilibili.view.fragment.FJuragment;
import com.wuliwei.newbilibili.view.fragment.MoveFragment;
import com.wuliwei.newbilibili.view.fragment.UPZhuFragment;
import com.wuliwei.newbilibili.view.fragment.ZhongHeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ib_sao)
    ImageButton ibSao;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragments;

    private SearchAdapter adapter;

    @Override
    protected void initListener() {
        etSearch.setSelection(etSearch.getText().length());
    }

    @Override
    protected void initData() {
        initFragment();
        initAdapter();

        String title = getIntent().getStringExtra("title");
        etSearch.setTextColor(Color.WHITE);
        etSearch.setText(title);
    }

    private void initAdapter() {

        //设置适配器
        adapter = new SearchAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ZhongHeFragment());
        fragments.add(new FJuragment());
        fragments.add(new UPZhuFragment());
        fragments.add(new MoveFragment());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick({R.id.ib_back, R.id.ib_sao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_sao:
                Toast.makeText(SearchActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
