package com.post.station.ui.homepage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.R;
import com.post.station.base.BaseActivity;
import butterknife.ButterKnife;

public class SmsHelpActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SmsHelpActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sms_help);
        ButterKnife.bind(this);
        setContentTitle("短信使用帮助");
    }
}
