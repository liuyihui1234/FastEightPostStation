package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.base.BaseActivity;

import butterknife.ButterKnife;

public class BusinessCardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       addContentView(R.layout.activity_sweep_order);
        ButterKnife.bind(this);
        setContentTitle("二维码名片");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, BusinessCardActivity.class));
    }
}
