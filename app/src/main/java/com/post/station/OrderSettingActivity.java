package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.base.BaseActivity;

public class OrderSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_order_setting);
        setContentTitle("订单设置");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, OrderSettingActivity.class));
    }
}
