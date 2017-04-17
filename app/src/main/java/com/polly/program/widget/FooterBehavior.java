package com.polly.program.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class FooterBehavior extends CoordinatorLayout.Behavior {

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 表示是否给应用了Behavior 的View 指定一个依赖的布局，通常，当依赖的View 布局发生变化时
     * 不管被被依赖View 的顺序怎样，被依赖的View也会重新布局
     *
     * @param parent
     * @param child      绑定behavior 的View
     * @param dependency 依赖的view
     * @return 如果child 是依赖的指定的View 返回true,否则返回false
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency instanceof AppBarLayout;
    }

    /**
     * 当被依赖的View 状态（如：位置、大小）发生变化时，这个方法被调用
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float scaleY = Math.abs(dependency.getY()) / dependency.getHeight();
        child.setTranslationY(child.getHeight() * scaleY);
        return true;
    }

}
