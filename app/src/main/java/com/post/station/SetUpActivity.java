package com.post.station;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.post.station.base.BaseActivity;
import com.post.station.ui.login.FoundPasswordActivity;
import com.post.station.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station
 *  创建时间：2019/9/2516:19
 *  作者：wpx
 *  描述：btn_logout
 */public class SetUpActivity extends BaseActivity {
    @BindView(R.id.ll_voice_reminder)
    LinearLayout ll_voice_reminder;
    @BindView(R.id.ll_help)
    LinearLayout ll_help;
    @BindView(R.id.btn_logout)
    Button btn_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_setup);
        setContentTitle("设置");
        ButterKnife.bind(this);

    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, SetUpActivity.class));
    }
    @OnClick({R.id.btn_logout,R.id.tv_change_password,R.id.ll_voice_reminder,R.id.ll_detention_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
               finish();
              LoginActivity.start(SetUpActivity.this);
              break;
            case R.id.tv_change_password:
                FoundPasswordActivity.start(SetUpActivity.this);
                break;
            case R.id.ll_voice_reminder:
                VoiceReminderActivity.start(SetUpActivity.this);
                break;
            case R.id.ll_detention_time:
                DetentionTimeActivity.start(SetUpActivity.this);
                break;


        }
    }

}
