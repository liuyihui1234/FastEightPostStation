package com.post.station.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.post.station.R;
import com.post.station.base.BaseActivity;

/*
 *  创建时间：2019/9/1814:57
 *  作者：wpx
 *  描述：
 */public class FeedBackActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, FeedBackActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_feedback);
        showAppBar(false);
    }
}
