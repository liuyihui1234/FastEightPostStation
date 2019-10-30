package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.base.BaseActivity;
import com.post.station.ui.homepage.SmsHelpActivity;

import butterknife.ButterKnife;

public class NewSignatureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_new_signature);
        ButterKnife.bind(this);
        setContentTitle("新建签名");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, NewSignatureActivity.class));
    }
}
