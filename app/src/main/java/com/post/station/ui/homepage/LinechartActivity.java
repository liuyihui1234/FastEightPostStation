package com.post.station.ui.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.MyWalletActivity;

public class LinechartActivity extends BaseActivity {
private TextView mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        mTitle=findViewById(R.id.mTitle);
    }

}
