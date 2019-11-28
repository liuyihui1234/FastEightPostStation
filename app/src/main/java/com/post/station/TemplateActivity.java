package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.post.station.base.BaseActivity;

public class TemplateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        setTitle("模板列表");
    }
}
