package com.polly.program.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.polly.program.R;

public class AnimationUtil {

    public static void horizontalStart(Activity mContext) {
        mContext.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_nor);
    }

    public static void horizontalFinish(Activity mContext) {
        mContext.overridePendingTransition(R.anim.slide_nor, R.anim.slide_right_out);
    }

    public static void verticalStart(Activity mContext) {
        mContext.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_nor);
    }

    public static void verticalFinish(Activity mContext) {
        mContext.overridePendingTransition(R.anim.slide_nor, R.anim.slide_out_bottom);
    }

    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    public static void anim(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, ImageView.SCALE_X, 0.1f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, ImageView.SCALE_Y, 0.1f, 1f);
        animator1.setDuration(500);
        animator2.setDuration(500);
        animator1.setInterpolator(OVERSHOOT_INTERPOLATOR);
        animator2.setInterpolator(OVERSHOOT_INTERPOLATOR);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2);
        set.start();
    }

}
