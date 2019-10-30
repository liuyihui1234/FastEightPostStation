package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.base.BaseActivity;
import com.post.station.ui.homepage.PaymentActivity;

import butterknife.ButterKnife;

public class SweepOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sweep_order);
        ButterKnife.bind(this);
        setContentTitle("扫码下单");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, SweepOrderActivity.class));
    }
}
