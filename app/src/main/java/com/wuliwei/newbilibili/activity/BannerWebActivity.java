package com.wuliwei.newbilibili.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wuliwei.newbilibili.R;
import com.wuliwei.newbilibili.base.BaseActivity;
import com.wuliwei.newbilibili.uitls.ClipboardUtil;

import butterknife.BindView;

public class BannerWebActivity extends BaseActivity {


    @BindView(R.id.share_toolbar)
    Toolbar shareToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.activity_web)
    LinearLayout activityWeb;

    private String link;
    private String title;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {

        title = getIntent().getStringExtra("title");
        link = getIntent().getStringExtra("link");

        shareToolbar.setTitle(TextUtils.isEmpty(title) ? "详情" : title);

        setSupportActionBar(shareToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        WebSettings webSettings = webview.getSettings();
        //启用 WebView 中的 Javascript 支持
        webSettings.setJavaScriptEnabled(true);
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);

        //设置检索缓存的
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
        //1.添加js接口,支持java和js之间的互相调用
        //2.写一个类里面写一个js调java的方法，注意这个方法名和参数类型要和js中的一样，否则无法调用
//        webview.addJavascriptInterface(new myJavaScriptInterface(),"cyc");

        //设置不跳转到系统的浏览器

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            /**
             *
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                progressbar.setVisibility(View.GONE);
            }
        });

        if (!TextUtils.isEmpty(link)) {
            webview.loadUrl(link);
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
                ClipboardUtil.setText(BannerWebActivity.this, link);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + link);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack() && webview.copyBackForwardList().getSize() > 0
                && !webview.getUrl().equals(webview.copyBackForwardList().getItemAtIndex(0)
                .getOriginalUrl())) {
            webview.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        webview.destroy();
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_banner_web;
    }

}
