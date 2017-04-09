package com.wuliwei.newbilibili.view.activity;

import android.content.Intent;
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
import com.wuliwei.newbilibili.uitls.AppNet;
import com.wuliwei.newbilibili.view.base.BaseActivity;
import com.wuliwei.newbilibili.view.base.BaseFragment1;
import com.wuliwei.newbilibili.view.fragment.ZhongHeFragment;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

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

    private ArrayList<BaseFragment1> fragments;

    private SearchAdapter adapter;

    private SearchFragment searchFragment;
    private String title;

    @Override
    protected void initData() {
        initFragment();
        initAdapter();

        title = getIntent().getStringExtra("title");
        etSearch.setTextColor(Color.WHITE);
        etSearch.setText(title);

        searchFragment = SearchFragment.newInstance();
    }

    @Override
    protected void initListener() {
        etSearch.setSelection(etSearch.getText().length());
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
//        fragments.add(new FJuragment());
//        fragments.add(new UPZhuFragment());
//        fragments.add(new MoveFragment());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick({R.id.ib_back, R.id.ib_sao,R.id.et_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_sao:
                Toast.makeText(SearchActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.et_search:
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        String str = AppNet.SOUSUO + keyword + AppNet.SUOSOUDOWN;
                        Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                        intent.putExtra("link", str);
                        intent.putExtra("title", keyword);
                        startActivity(intent);
                    }
                });
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
//                String str = searchFragment.getActivity().getIntent().getStringExtra("title");
//                etSearch.setTextColor(Color.BLACK);
//                etSearch.setText(str);
                break;
        }
    }
}
