package com.polly.program.util;

import android.view.View;

/**
 * @author linfeizheng
 * @date 2017/3/6 15:47
 */

public abstract class OnClickEvent implements View.OnClickListener {

    private static long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastClickTime) > 1000) {
            v.setEnabled(false);
            onSingleClick(v);
        }
        lastClickTime = currentTime;
    }

    public abstract void onSingleClick(View v);
}
