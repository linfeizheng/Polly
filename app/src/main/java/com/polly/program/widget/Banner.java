package com.polly.program.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.polly.program.R;
import com.polly.program.base.BasePagerAdapter;
import com.polly.program.util.ScreenUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linfeizheng on 2017/4/18.
 */

public class Banner extends FrameLayout implements OnPageChangeListener {

    private Context context;
    private int size;
    private int time = 5000;
    private boolean isLoop;

    @Bind(R.id.viewpager_looper)
    ViewPager mViewPager;
    @Bind(R.id.llyt_looper_dot)
    LinearLayout mDotLayout;
    private Handler mHandler;

    public Banner(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    public Banner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_looper_viewpager, this, true);
        int screenWidth = ScreenUtil.getScreenWidth(context);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (screenWidth * 0.4)));
        ButterKnife.bind(this, view);
    }

    public void setAdapter(BasePagerAdapter<?> pagerAdapter) {
        size = pagerAdapter.getOriginalCount();
        initDots();
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(size);
        mViewPager.setCurrentItem(100 * size);
        updateIntroAndDot();
        mViewPager.addOnPageChangeListener(this);
        if (mHandler == null && isLoop) {
            mHandler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    mHandler.sendEmptyMessageDelayed(0, time);
                }
            };
            mHandler.sendEmptyMessageDelayed(0, time);
        }
    }

    private void initDots() {
        mDotLayout.removeAllViews();
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                View view = new View(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                if (i != 0) {
                    params.leftMargin = 15;
                }
                view.setLayoutParams(params);
                view.setBackgroundResource(R.drawable.selector_dot);
                mDotLayout.addView(view);
            }
        }
    }

    private void updateIntroAndDot() {
        int currentPage = mViewPager.getCurrentItem() % size;
        for (int i = 0; i < mDotLayout.getChildCount(); i++) {
            mDotLayout.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int arg0) {
        updateIntroAndDot();
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

}