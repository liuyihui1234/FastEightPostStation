package com.post.station;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.post.station.widget.PhotoPopupWindow;

import rx.functions.Action1;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/11/615:25
 *  作者：wpx
 *  描述：
 */public class StorageRecordPopupWindow extends PopupWindow implements View.OnClickListener {
    private View mPopView;
    private TextView tv_success;
    public  TextView tv_submission;
    private TextView tv_whole;
    private TextView tv_fail;
    private OnItemClickListener mListener;
    private Action1<Boolean> action1;
    private Action1<String> action2;
    public StorageRecordPopupWindow(Activity context,Action1<Boolean> action1,Action1<String> action2) {
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
        mPopView = inflater.inflate(R.layout.storage_record_popup_window, null);
        tv_success = mPopView.findViewById(R.id.tv_success);
        tv_submission = mPopView.findViewById(R.id.tv_submission);
        tv_whole = mPopView.findViewById(R.id.tv_whole);
        tv_fail = mPopView.findViewById(R.id.tv_fail);
    }
    @SuppressLint("InlinedApi")
    private void setPopupWindow(Activity context) {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
//        this.setAnimationStyle(R.style.mypopwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        this.setOutsideTouchable(true);
    }


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
