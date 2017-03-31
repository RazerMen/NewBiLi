package com.wuliwei.newbilibili.activity;

import android.content.Intent;
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

import com.anye.greendao.gen.UserDao;
import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.app.MyApplication;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.uitls.User;

import java.util.List;

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
    private String userName;
    private String passWord;
    private UserDao userDao;
    private List<User> users;


    @Override
    protected void initListener() {
        initShow();
    }

    @Override
    protected void initData() {
        name.setHint("请输入用户名");
        mima.setHint("请输入密码");

        etUserName.setSelection(etUserName.getText().length());
        etPassWord.setSelection(etPassWord.getText().length());

        userDao = MyApplication.getInstances().getDaoSession().getUserDao();


    }

    private void initZhuCe() {
        userName = etUserName.getText().toString().trim();
        passWord = etPassWord.getText().toString().trim();

        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);

        UserDao userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        long insert = userDao.insert(user);
        if (insert == 1) {
            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        } else if (insert == -1) {
            Toast.makeText(LoginActivity.this, "用户名已经存在", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "!", Toast.LENGTH_SHORT).show();
        }
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
                userName = etUserName.getText().toString().trim();
                passWord = etPassWord.getText().toString().trim();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
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
                userName = etUserName.getText().toString().trim();
                passWord = etPassWord.getText().toString().trim();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
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
                users = userDao.loadAll();
                String userNames = etUserName.getText().toString().trim();
                if (users.size() == 0) {
                    User user = new User(etUserName.getText().toString().trim(), etPassWord.getText().toString().trim());
                    userDao.insert(user);
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    for (int i = 0; i < users.size(); i++) {
                        if (userNames.equals(users.get(i).getUserName().toString())) {
                            Toast.makeText(LoginActivity.this, "该帐号已经存在", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    User user = new User(etUserName.getText().toString().trim(), etPassWord.getText().toString().trim());
                    userDao.insert(user);
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_login:
//                Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
                userName = etUserName.getText().toString().trim();
                passWord = etPassWord.getText().toString().trim();
                List<User> user = userDao.loadAll();
                for (User list : user) {
                    String userName1 = list.getUserName();
                    String number = list.getPassWord();
                    if (userName.equals(userName1) && passWord.equals(number)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Toast.makeText(LoginActivity.this, "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
