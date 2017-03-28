package com.wuliwei.newbilibili.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.fragment.CommunityShoppingFragment;
import com.wuliwei.newbilibili.fragment.HomeShoppingFragment;
import com.wuliwei.newbilibili.fragment.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShoppingActivity extends BaseActivity {

    @BindView(R.id.share_toolbar)
    Toolbar shareToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    private List<BaseFragment> fragments;

    //被选中页面的位置
    private int position;

    //刚才被显示的Fragment,也就是缓存的Fragment
    private Fragment tempFragment;

    @Override
    protected void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    //对应的Fragmeng的位置
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_cart:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                }

                //得到当前的Fragment,根据位置切换到对应的Fragment
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        //设置默认页面
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment currentFragment) {

        // 如果之前的和当前的Fragment不相同，不是同一个页面的时候
        if (tempFragment != currentFragment) {

            //得到FragmentManager:代表一个事物
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (!currentFragment.isAdded()) {
                //隐藏缓存
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //添加
                ft.add(R.id.fl_main, currentFragment);
            } else {
                //隐藏缓存
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //显示
                ft.show(currentFragment);
            }
            //提交事物
            ft.commit();

            //把当前的 赋值成 缓存的
            tempFragment = currentFragment;
        }
    }

    @Override
    protected void initData() {
        initWeb();
        //初始化Fragment
        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeShoppingFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new CommunityShoppingFragment());
    }

    private void initWeb() {
        shareToolbar.setTitle("bilibili-周边商城");
        setSupportActionBar(shareToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_share:
                share();
                break;
            case R.id.menu_browseropen:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.menu_copylink:
//                ClipboardUtil.setText(BannerWebActivity.this, link);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + link);
        startActivity(intent);
    }

//    @Override
//    public void onBackPressed() {
//        if (webview.canGoBack() && webview.copyBackForwardList().getSize() > 0
//                && !webview.getUrl().equals(webview.copyBackForwardList().getItemAtIndex(0)
//                .getOriginalUrl())) {
//            webview.goBack();
//        } else {
//            finish();
//        }
//    }

//    @Override
//    protected void onDestroy() {
//        webview.destroy();
//        super.onDestroy();
//    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping;
    }
}
