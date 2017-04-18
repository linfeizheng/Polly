package com.polly.program.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by linfeizheng on 2017/4/18.
 */

public class Utils {

    public static void share(Activity activity, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(intent, "分享"));
    }

}
