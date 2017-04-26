package com.polly.program.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class ApplicationUtil {

    private final static List<Activity> activities = new ArrayList<>();

    /**
     * 加入Activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除Activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 销毁其它Activity
     */
    public static void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    /**
     * 获取当前程序的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取当前程序的版本号
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 设备的IMEI
     */
    public static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            Logger.e(e, "获取IMEI失败");
        }
        return null;
    }
}