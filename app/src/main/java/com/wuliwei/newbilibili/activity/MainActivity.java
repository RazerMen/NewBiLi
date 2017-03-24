package com.wuliwei.newbilibili.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.adapter.DirectseedingAdapter;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.base.BaseFragment;
import com.wuliwei.newbilibili.fragment.DirectseedingFragment;
import com.wuliwei.newbilibili.fragment.FindFragment;
import com.wuliwei.newbilibili.fragment.PartitionFragment;
import com.wuliwei.newbilibili.fragment.RecommendFragment;
import com.wuliwei.newbilibili.fragment.TrackFragment;
import com.wuliwei.newbilibili.view.CircleImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_no_login)
    TextView tvNoLogin;
    @BindView(R.id.iv_game)
    ImageView ivGame;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

    private ArrayList<BaseFragment> fragments;

    private DirectseedingAdapter adapter;

    @Override
    protected void initListener() {
//        drawerLayout = new ActionBarDrawerToggle(this,drawerLayout,R.string.)
    }

    @Override
    protected void initData() {
        initFragnment();
        initAdapter();
    }

    private void initAdapter() {

        adapter = new DirectseedingAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private void initFragnment() {

        fragments = new ArrayList<>();
        fragments.add(new DirectseedingFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new TrackFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new FindFragment());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.iv_home, R.id.iv_head, R.id.tv_no_login, R.id.iv_game, R.id.iv_down, R.id.iv_select, R.id.toolBar, R.id.tabLayout, R.id.appBar, R.id.viewPager, R.id.coordinatorLayout, R.id.nav_view, R.id.drawerLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
//                Toast.makeText(MainActivity.this, "侧滑菜单", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.iv_head:
//                Toast.makeText(MainActivity.this, "头像", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.tv_no_login:
//                Toast.makeText(MainActivity.this, "登录", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.iv_game:
                Toast.makeText(MainActivity.this, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_down:
                Toast.makeText(MainActivity.this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_select:
                Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolBar:
                break;
            case R.id.tabLayout:
                break;
            case R.id.appBar:
                break;
            case R.id.viewPager:
                break;
            case R.id.coordinatorLayout:
                break;
            case R.id.nav_view:
                break;
            case R.id.drawerLayout:
                break;
        }
    }

    /**
     * 双击退出
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
