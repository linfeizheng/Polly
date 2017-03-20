package com.polly.program;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.init(BuildConfig.APPLICATION_ID).logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
        Bugly.init(getApplicationContext(), "b68313baa9", false);
        LeakCanary.install(this);
        initTbs();
    }

    public static Context getInstance() {
        return instance;
    }

    private void initTbs() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.i("Application", "View是否初始化完成:" + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                Log.i("Application", "X5内核初始化完成");
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.i("Application", "腾讯X5内核 下载结束");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.i("Application", "腾讯X5内核 安装完成");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.i("Application", "腾讯X5内核 下载进度:%" + i);
            }
        });
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

}
