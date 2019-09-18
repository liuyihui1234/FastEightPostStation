package com.post.station.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.post.station.MainActivity;
import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.model.LoginModel;
import com.post.station.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity {

    Unbinder unbinder;

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_login);
        setContentTitle("驿站登录");
        showBackButton(false);
        unbinder = ButterKnife.bind(this);
    }

//    @OnClick({R.id.ivNetApply, R.id.tvForgetPwd, R.id.rl_login, R.id.iv_close})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.ivNetApply:
//                RegisterActivity.start(this);
//                break;
//            case R.id.tvForgetPwd:
//                FoundPasswordActivity.start(this);
//                break;
//            case R.id.rl_login:
//                login();
//                //MainActivity.start(this);
//                break;
//            case R.id.iv_close:
//                finish();
//                //LoginWaysActivity.start(this);
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
