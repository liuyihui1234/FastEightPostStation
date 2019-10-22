package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.OrderSettingActivity;
import com.post.station.R;
import com.post.station.base.BaseActivity;

public class AddCustomerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_add_customer);
        setContentTitle("添加客户");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, AddCustomerActivity.class));
    }
}
