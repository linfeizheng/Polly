package com.polly.program.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 宽高一致的ImageView
 */
public class EqualSizeImageView extends android.support.v7.widget.AppCompatImageView {

    public EqualSizeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EqualSizeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public EqualSizeImageView(Context context) {
        this(context, null);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}