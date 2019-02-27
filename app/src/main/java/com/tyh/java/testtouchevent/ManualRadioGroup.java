package com.tyh.java.testtouchevent;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 拦截了RadioButton点击事件的RadioGroup
 * 如果要选中RadioButton,请使用{@link #setOnChildRadioButtonClickedListener(OnChildRadioButtonClickedListener)},手动选中
 */
public class ManualRadioGroup extends RadioGroup {
    /**
     * 手指落下的位置所在的子view
     */
    View v = null;
    /**
     * 同一个事件序列中,经历过ACTION_MOVE则为true
     */
    private boolean moved;

    public ManualRadioGroup(Context context) {
        super(context);
    }

    public ManualRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            v = findViewByXY(ev.getX(), ev.getY());
            if (v != null && v instanceof RadioButton) {
                /**如果手指落下的位置刚好在一个RadioButton上,就直接丢到自己的{@link #onTouchEvent(MotionEvent)}方法处理*/
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moved = false;
                //消费事件
                return true;
            case MotionEvent.ACTION_UP:
                if (!moved) {//如果手指没有移动过,就是点击了RadioButton
                    if (listener != null) {
                        if (getCheckedRadioButtonId() == -1) {
                            listener.onRadioButtonClickedWhenCheckedNone((RadioButton) v);
                        } else if (getCheckedRadioButtonId() == v.getId()) {
                            listener.onRadioButtonCheckedClicked((RadioButton) v);
                        } else {
                            listener.onRadioButtonDifferentFromCheckedClicked((RadioButton) v, (RadioButton) findViewById(getCheckedRadioButtonId()));
                        }
                    }
                    //消费事件
                    return true;
                } else {
                    //丢给父类处理
                    return super.onTouchEvent(event);
                }
            case MotionEvent.ACTION_MOVE:
                //手指移动过了
                moved = true;
                //丢给父类处理
                return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private View findViewByXY(float x, float y) {
        View v = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = new Rect(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (!rect.contains((int) x, (int) y)) {
                continue;
            }
            v = child;
            break;
        }

        return v;
    }

    private OnChildRadioButtonClickedListener listener;

    public interface OnChildRadioButtonClickedListener {
        /**
         * 没有按钮被选中时,被点击了
         *
         * @param button 被点击的按钮
         */
        void onRadioButtonClickedWhenCheckedNone(RadioButton button);

        /**
         * 已选中的RadioButton被点击了
         *
         * @param button 被点击的按钮
         */
        void onRadioButtonCheckedClicked(RadioButton button);

        /**
         * 非选中的RadioButton被点击了
         *
         * @param clickedRadioButton 被点击的按钮
         * @param checkedRadioButton 已经选中的按钮
         */
        void onRadioButtonDifferentFromCheckedClicked(RadioButton clickedRadioButton, RadioButton checkedRadioButton);
    }

    public OnChildRadioButtonClickedListener getOnChildRadioButtonClickedListener() {
        return listener;
    }

    public void setOnChildRadioButtonClickedListener(OnChildRadioButtonClickedListener listener) {
        this.listener = listener;
    }
}
