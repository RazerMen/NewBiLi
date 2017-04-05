package com.wuliwei.newbilibili.view.activity;

import android.content.Intent;
import android.os.Handler;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.view.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
