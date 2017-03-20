package com.polly.program.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.orhanobut.logger.Logger;

public class SharedPreferencesUtil {

    /**
     * 存储文件名
     */
    public final static String SHAREDPREFERENCES_FILE = "program.xml";

    public static final String HOME_TAB_INFO = "HOME_TAB_INFO";

    private SharedPreferences sp;
    private Editor editor;

    public SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(SHAREDPREFERENCES_FILE, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void put(String key, Object object) {
        try {
            if (object instanceof String) {
                editor.putString(key, (String) object);
            } else if (object instanceof Integer) {
                editor.putInt(key, (Integer) object);
            } else if (object instanceof Boolean) {
                editor.putBoolean(key, (Boolean) object);
            } else if (object instanceof Float) {
                editor.putFloat(key, (Float) object);
            } else if (object instanceof Long) {
                editor.putLong(key, (Long) object);
            } else {
                editor.putString(key, object.toString());
            }
        } catch (Exception e) {
            Logger.e("SharedPreferences存入失败", e);
        }
        editor.commit();
    }

    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        Logger.e("没有读取到shared值：key-->" + key);
        return null;
    }

    public void saveHomeTab(String homeTab) {
        editor.putString(HOME_TAB_INFO, homeTab).commit();
    }

    public String getHomeTab() {
        return sp.getString(HOME_TAB_INFO, "");
    }

}
