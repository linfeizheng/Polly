package com.polly.program.ui.main.home.list;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.polly.program.base.BasePagerAdapter;
import com.polly.program.util.ImageUtil;

/**
 * Created by linfeizheng on 2017/4/18.
 */

public class BannerAdapter extends BasePagerAdapter<String> {

    public BannerAdapter(Context context, String[] list) {
        super(context, list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        final String imageUrl = list[position % list.length];
        ImageUtil.loadImg(mContext, imageUrl, imageView);
        container.addView(imageView);
        return imageView;
    }
}
