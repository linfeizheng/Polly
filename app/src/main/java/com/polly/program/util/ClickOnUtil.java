package com.polly.program.util;

public class ClickOnUtil {

    private static long lastClickTime = 0;

    /**
     * 是否快速双击
     *
     * @return
     */
    public static boolean isDoubleClickQuickly() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastClickTime) < 500) {
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

}
