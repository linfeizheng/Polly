package com.polly.program.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class ImageUtil {

    public static void loadImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public static void loadCircleImg(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void loadCornerImg(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CornersTransform(context))
//                .placeholder(R.drawable.ic_gf_default_photo)
//                .error(R.drawable.ic_gf_default_photo)
                .crossFade()
                .into(imageView);
    }

    public static void loadGif(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

}
