package com.polly.program.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.polly.program.R;

/**
 * Created by linfeizheng on 2017/4/24.
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private int offset = 0;
    private int startOffset;
    private int endOffset;
    private Context mContext;
    private TextView mTvTitle;

    private int RED;
    private int GREEN;
    private int BLUE;

    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, Toolbar toolbar, int layoutDirection) {
        mTvTitle = (TextView) toolbar.findViewById(R.id.tv_title);
        startOffset = 0;
        endOffset = mContext.getResources().getDimensionPixelOffset(R.dimen.movie_header_height) - toolbar.getHeight();
        int backgroundColor = ContextCompat.getColor(mContext, R.color.colorPrimary);
        RED = Color.red(backgroundColor);
        GREEN = Color.green(backgroundColor);
        BLUE = Color.blue(backgroundColor);
        return super.onLayoutChild(parent, toolbar, layoutDirection);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        offset += dyConsumed;
        if (offset <= startOffset) {  //alpha为0
            toolbar.setBackgroundColor(Color.argb(0, RED, GREEN, BLUE));
        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
            float precent = (float) (offset - startOffset) / endOffset;
            int alpha = (int) (precent * 255);
            toolbar.setBackgroundColor(Color.argb(alpha, RED, GREEN, BLUE));
            mTvTitle.setVisibility(View.INVISIBLE);
        } else if (offset >= endOffset) {  //alpha为255
            toolbar.setBackgroundColor(Color.argb(255, RED, GREEN, BLUE));
            mTvTitle.setVisibility(View.VISIBLE);
        }
    }

}