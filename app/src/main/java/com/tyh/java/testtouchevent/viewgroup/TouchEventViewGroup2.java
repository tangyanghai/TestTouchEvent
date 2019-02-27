package com.tyh.java.testtouchevent.viewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.tyh.java.testtouchevent.LogUtil;

/**
 * 创建人: tyh
 * 创建时间: 2019/2/26
 * 描述:
 */
public class TouchEventViewGroup2 extends LinearLayout {

    public TouchEventViewGroup2(Context context) {
        super(context);
    }

    public TouchEventViewGroup2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.logMethod();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.logMethod();
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.logMethod();
        return true;
    }
}
