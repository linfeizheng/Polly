package com.polly.program.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polly.program.R;
import com.polly.program.util.ScreenUtil;

/**
 * @author linfeizheng
 * @date 2017/1/12 14:16
 */

public class FooterView extends TextView {

    public FooterView(Context context) {
        this(context, null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(context, 50));
        setLayoutParams(params);
        setTextColor(getResources().getColor(R.color.font_gray1));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        setGravity(Gravity.CENTER);
        setText("加载中...");
    }
}
