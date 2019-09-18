package com.post.station.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.ButterKnife;
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
