package com.post.station.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.post.station.R;
import com.post.station.view.WaveView;

import rx.functions.Action1;

public class SpeechRecognitionPopupWindow extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private TextView tv_send_order;
    private OnItemClickListener mListener;
    private Action1<Boolean> action1;
    private Action1<String> action2;
    private WaveView waveView;

    public SpeechRecognitionPopupWindow(Activity context,Action1<Boolean> action1,Action1<String> action2) {
        super(context);        // TODO Auto-generated constructor stub
        init(context);
        this.action1 = action1;
        this.action2 = action2;
        setPopupWindow(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);        //绑定布局
        mPopView = inflater.inflate(R.layout.speech_recognition_popup_window, null);
        tv_send_order = mPopView.findViewById(R.id.tv_send_order);
        waveView = mPopView.findViewById(R.id.wave_view);
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow(Activity context) {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
//        this.setAnimationStyle(R.style.mypopwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        this.setOutsideTouchable(true);

        tv_send_order.setOnClickListener(view -> {
            dismiss();
            waveView.setVisibility(View.GONE);
            if (action2 != null) action2.call("");
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < bytes.length; i++) {
//                    byte a = 1;
//                    if (a < 100) {
//                        a++;
//                    } else {
//                        a--;
//                    }
//                    bytes[i] = a;
                    waveView.addData(bytes[i]);
                }
            }
        }).start();
    }

    byte[] bytes = {97, 98, 100, 88, 55, 100, 20, 99, 100, 88, 80, 99, 98, 40, 60, 70, 40, 50, 40, 97, 98, 100, 88, 55, 100,
            20, 99, 100, 88, 80, 77, 88, 98, 40, 60, 70, 40, 50, 40, 20, 99, 100, 88, 80, 77, 00, 20, 99, 100, 88, 80, 99, 98, 40, 60, 70, 40, 50, 40, 97, 98, 100,
            11, 33, 44, 56, 78, 44, 88, 90, 99, 97, 40, 20, 99, 100, 88, 80, 77, 00, 20, 99, 100, 88, 80, 77, 88, 98, 40, 60, 70, 40, 50, 40, 20, 99, 100, 88,
            80, 77, 00, 20, 99, 100, 88, 80, 11, 33, 44, 56, 78, 44, 88, 90, 99, 97, 40, 20, 99, 100, 88, 80, 77, 00, 20, 99, 100, 88, 80, 77, 88, 98, 40, 60
            , 7,0, 99, 100, 88, 80, 77, 00, 20, 99, 100, 88, 80, 77, 88, 98, 40, 60, 70, 40, 50, 40, 20, 99, 100, 88,};

    public void show() {
        showAsDropDown(mPopView);
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {        // TODO Auto-generated method stub
        if (mListener != null) {
            mListener.setOnItemClick(v);
        }
    }
}