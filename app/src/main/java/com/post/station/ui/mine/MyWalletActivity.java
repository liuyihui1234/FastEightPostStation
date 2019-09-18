package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.R;
import com.post.station.ui.login.LoginActivity;

public class MyWalletActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MyWalletActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
    }
}
