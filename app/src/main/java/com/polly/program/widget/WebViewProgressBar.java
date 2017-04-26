package com.polly.program.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.polly.program.R;

public class WebViewProgressBar extends View {
    private int progress = 1;//进度默认为1
    private final static int HEIGHT = 3; //进度条高度为3
    private Paint paint;//进度条的画笔

    public WebViewProgressBar(Context context) {
        this(context, null);
    }

    public WebViewProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebViewProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);// 填充方式为描边
        paint.setStrokeWidth(HEIGHT);//设置画笔的宽度
        paint.setAntiAlias(true);// 抗锯齿
        paint.setDither(true);// 使用抖动效果
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));//画笔设置颜色
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() * progress / 100, HEIGHT, paint);
    }
}
