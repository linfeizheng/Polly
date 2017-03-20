package com.polly.program.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class CustomWebView extends WebView {
    private WebViewProgressBar progressBar;//进度条的矩形（进度线）
    private Handler handler;
    private String appCacheDir;
    public TextView mTvTitle;

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //实例化进度条
        progressBar = new WebViewProgressBar(context);
        //设置进度条的size
        progressBar.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //刚开始时候进度条不可见
        progressBar.setVisibility(GONE);
        //把进度条添加到webView里面
        addView(progressBar);
        //初始化handle
        handler = new Handler();
        initSettings();
        appCacheDir = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
    }

    private void initSettings() {
        // 初始化设置
        WebSettings mSettings = this.getSettings();
        mSettings.setJavaScriptEnabled(true);//开启javascript
        mSettings.setBlockNetworkImage(false);
        mSettings.setDomStorageEnabled(true);//开启DOM
        mSettings.setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
//        mSettings.setDatabasePath(appCacheDir);
//        mSettings.setDefaultTextEncodingName("utf-8");//设置字符编码
        //设置web页面
//        mSettings.setAllowFileAccess(true);//设置支持文件流
        // 设置可以支持缩放
//        mSettings.setSupportZoom(true);
        // 设置可以支持缩放
//        mSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        mSettings.setDisplayZoomControls(false);
        //扩大比例的缩放 Android4.1，放大功能出现了异常
        mSettings.setUseWideViewPort(true);
        //自适应屏幕
//        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//设置 缓存模式
        mSettings.setLoadWithOverviewMode(true);// 调整到适合webview大小
        mSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        mSettings.setAppCacheEnabled(true);//开启缓存机制
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);//js和android交互

//        if (Build.VERSION.SDK_INT >= 19) {
//            mSettings.setLoadsImagesAutomatically(true);
//        } else {
//            //导致android4.4系统下图片无法加载出来
//            mSettings.setLoadsImagesAutomatically(false);
//        }
        mSettings.setLoadsImagesAutomatically(true);//自动加载图片

        setWebChromeClient(new MyWebChromeClient());
//        setWebViewClient(new MyWebViewClient());
    }

    /**
     * 自定义WebChromeClient
     */
    public class MyWebChromeClient extends WebChromeClient {
        /**
         * 进度改变的回掉
         *
         * @param view        WebView
         * @param newProgress 新进度
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            System.out.println("加载进度：" + newProgress);
            if (newProgress == 100) {
                progressBar.setProgress(100);
                handler.postDelayed(runnable, 200);//0.2秒后隐藏进度条
            } else if (progressBar.getVisibility() == GONE) {
                progressBar.setVisibility(VISIBLE);
            }
            //设置初始进度10，这样会显得效果真一点，总不能从1开始吧
            if (newProgress < 10) {
                newProgress = 10;
            }
            //不断更新进度
            progressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mTvTitle != null)
                mTvTitle.setText(title);
        }
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            loadUrl(s);
            return true;
        }
    }

    /**
     * 刷新界面（此处为加载完成后进度消失）
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progressBar.setVisibility(View.GONE);
        }
    };

    public void setTitleView(TextView textView) {
        mTvTitle = textView;
    }
}
