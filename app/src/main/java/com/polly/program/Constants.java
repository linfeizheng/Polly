package com.polly.program;

import android.os.Environment;

import com.orhanobut.logger.Logger;

public class Constants {

    /**
     * 本项目在SD卡的文件夹
     */
    public final static String PATH_ON_SD_CARD_OF_APP = Environment.getExternalStorageDirectory() + "/program/";
    /**
     * 存放缓存
     */
    public final static String PATH_CACHE_ON_SD_CARD_OF_APP = PATH_ON_SD_CARD_OF_APP + "cache/";

    public final static String INTENT_EXTRA_ID = "INTENT_EXTRA_ID";
    public final static String INTENT_EXTRA_TITLE = "INTENT_EXTRA_TITLE";
    public final static String INTENT_EXTRA_OBJECT = "INTENT_EXTRA_OBJECT";

    public static final int NORMAL_REQUESTCODE = 99;


    public static String getHomeUrl(String source) {
        String url = "https://juejin.im/tag/" + source;
        Logger.d("Jsoup-->" + url);
        return url;
    }

    public static final String GIF_URL = "http://gank.io/api/data/福利/10/";
}
