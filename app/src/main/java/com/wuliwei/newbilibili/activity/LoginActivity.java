package com.wuliwei.newbilibili.activity;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_more)
    TextView ibMore;
    @BindView(R.id.ll_titles)
    LinearLayout llTitles;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_us)
    ImageView ivUs;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.name)
    TextInputLayout name;
    @BindView(R.id.iv_ps)
    ImageView ivPs;
    @BindView(R.id.et_passWord)
    EditText etPassWord;
    @BindView(R.id.mima)
    TextInputLayout mima;
    @BindView(R.id.btn_zhuce)
    Button btnZhuce;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initListener() {
        initShow();
    }

    @Override
    protected void initData() {
        name.setHint("请输入用户名");
        mima.setHint("请输入密码");
    }

    private void initShow() {

        initDianJi();

        initPanDuan();
    }

    private void initDianJi() {
        etUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    // 获得焦点
                    ivLeft.setImageResource(R.drawable.ic_22);
                    ivRight.setImageResource(R.drawable.ic_33);

                } else {
                    // 失去焦点
                    ivLeft.setImageResource(R.drawable.ic_22_hide);
                    ivRight.setImageResource(R.drawable.ic_33_hide);
                }
            }
        });
    }

    private void initPanDuan() {
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etUserName.getText().toString()) && !TextUtils.isEmpty(etPassWord.getText().toString())) {
                    btnLogin.setClickable(true);
                    btnLogin.setBackgroundColor(Color.parseColor("#FB7299"));

                } else {
                    btnLogin.setClickable(false);
                    btnLogin.setBackgroundColor(Color.parseColor("#3B3B3B"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etUserName.getText().toString()) && !TextUtils.isEmpty(etPassWord.getText().toString())) {
                    btnLogin.setClickable(true);
                    btnLogin.setBackgroundColor(Color.parseColor("#FB7299"));

                } else {
                    btnLogin.setClickable(false);
                    btnLogin.setBackgroundColor(Color.parseColor("#3B3B3B"));
                }
//
//                if(!TextUtils.isEmpty(etPassWord.getText().toString()) && TextUtils.isEmpty(etUserName.getText().toString())) {
//                    btnLogin.setClickable(false);
//                    btnLogin.setBackgroundColor(Color.parseColor("#3B3B3B"));
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.ib_back, R.id.ib_more, R.id.iv_left, R.id.iv_right, R.id.btn_zhuce, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_more:
                Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_left:
                break;
            case R.id.iv_right:
                break;
            case R.id.btn_zhuce:
                Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login:
                Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
