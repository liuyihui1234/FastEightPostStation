package com.post.station.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.post.station.R;


public class EmptyViewLayout extends LinearLayout {

    public EmptyViewLayout(Context context) {
        this(context, null);
    }

    public EmptyViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.empty_layout, null);
        addView(view);
    }
}

