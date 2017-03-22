package com.wuliwei.newbilibili;

import android.content.Intent;
import android.os.Handler;

import com.wuliwei.newbilibili.activity.MainActivity;
import com.wuliwei.newbilibili.base.BaseActivity;

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
