package com.polly.program.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.polly.program.R;

public class ToastEx extends Toast {

    public ToastEx(Context context) {
        super(context);
    }

    private static ToastEx instance;

    /**
     * 初始化Toast
     */
    public static ToastEx getInstance(Context context) {
        if (instance == null) {
            instance = new ToastEx(context);
            View v = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            instance.setView(v);
            instance.setDuration(LENGTH_SHORT);
        }
        return instance;
    }
}