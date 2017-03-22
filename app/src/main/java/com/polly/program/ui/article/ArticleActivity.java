package com.polly.program.ui.article;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.polly.program.Constants;
import com.polly.program.R;
import com.polly.program.base.BaseActivity;
import com.polly.program.util.OnClickEvent;
import com.polly.program.widget.CustomWebView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;

public class ArticleActivity extends BaseActivity {

    @Bind(R.id.iv_title_share)
    ImageView mIvShare;
    @Bind(R.id.webview_article)
    CustomWebView mWebView;

    private String title;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    protected void initTitleBar() {
        if (title != null) {
            setTitle(title);
        }
        setBack();
        mIvShare.setVisibility(View.VISIBLE);
        mIvShare.setOnClickListener(new OnClickEvent() {
            @Override
            public void onSingleClick(View v) {
                share(title + " " + url);
            }
        });
    }

    @Override
    protected void initData() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        url = getIntent().getStringExtra(Constants.INTENT_EXTRA_ID);
        title = getIntent().getStringExtra(Constants.INTENT_EXTRA_TITLE);
        if (url != null)
            mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_ID, s);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        if (mWebView != null)
            mWebView = null;
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void share(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "分享"));
    }

}
