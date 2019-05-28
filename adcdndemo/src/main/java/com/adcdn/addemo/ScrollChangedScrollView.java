package com.adcdn.addemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author : xnn
 * @date : 2018/6/29
 * @description : 滚动监听ScrollView
 */

public class ScrollChangedScrollView extends ScrollView {
    private OnCustomScrollChangedListener customScrollChangedListener;

    public ScrollChangedScrollView(Context context) {
        this(context, null);
    }

    public ScrollChangedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollChangedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (customScrollChangedListener != null) {
            customScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnCustomScrollChangedListener(OnCustomScrollChangedListener customScrollChangedListener) {
        this.customScrollChangedListener = customScrollChangedListener;
    }

    public interface OnCustomScrollChangedListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
