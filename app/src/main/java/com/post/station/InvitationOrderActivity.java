package com.post.station;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.base.BaseActivity;

import butterknife.ButterKnife;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/11/99:31
 *  作者：wpx
 *  描述：
 */public class InvitationOrderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sweep_order);
        ButterKnife.bind(this);
        setContentTitle("邀请下单");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, InvitationOrderActivity.class));
    }
}
