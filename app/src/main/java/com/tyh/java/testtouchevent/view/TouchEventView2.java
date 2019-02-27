package com.tyh.java.testtouchevent.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tyh.java.testtouchevent.LogUtil;

/**
 * 创建人: tyh
 * 创建时间: 2019/2/26
 * 描述:
 */
public class TouchEventView2 extends View {
    public TouchEventView2(Context context) {
        super(context);
    }

    public TouchEventView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.logMethod();
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.logMethod();
//        return super.onTouchEvent(event);
        return false;
    }

}
