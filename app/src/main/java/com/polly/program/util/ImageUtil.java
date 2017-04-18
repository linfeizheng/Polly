package com.polly.program.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageUtil {

    /**
     * 普通图片，包括gif
     */
    public static void loadImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    /**
     * 圆形图片
     */
    public static void loadCircleImg(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }

    /**
     * 圆角图片
     */
    public static void loadCornerImg(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_gf_default_photo)
//                .error(R.drawable.ic_gf_default_photo)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, 10, 0))
                .into(imageView);
    }

    public static void loadGif(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    /**
     * 高斯模糊图片
     */
    public static void loadBlurImg(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_gf_default_photo)
//                .error(R.drawable.ic_gf_default_photo)
                .crossFade()
                .bitmapTransform(new BlurTransformation(context, 23, 4))
                .into(imageView);
    }

}
