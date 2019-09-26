package com.post.station;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.BrandManagementaActivity;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/9/2516:19
 *  作者：wpx
 *  描述：
 */public class SetUpActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, SetUpActivity.class));
    }
}
