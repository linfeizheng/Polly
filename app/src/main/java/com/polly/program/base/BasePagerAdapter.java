package com.polly.program.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by linfeizheng on 2017/4/18.
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected Context mContext;
    protected String[] list;

    public BasePagerAdapter(Context context, String[] list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null || list.length == 0) {
            return 0;
        } else if (list.length == 1) {
            return 1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public int getOriginalCount() {
        if (list == null) {
            return 0;
        } else {
            return list.length;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public abstract Object instantiateItem(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}