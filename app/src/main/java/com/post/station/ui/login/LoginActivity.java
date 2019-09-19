package com.post.station.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.post.station.MainActivity;
import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_agree)
    ImageView iv_agree;
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

    private boolean isChecked = false;

    @OnClick({R.id.rl_login, R.id.tvForgetPwd, R.id.iv_login_weixin,
            R.id.tv_new_user, R.id.iv_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgetPwd:
                FoundPasswordActivity.start(this);
                break;
            case R.id.rl_login:
                login();
                break;
            case R.id.tv_new_user:
                RegisterActivity.start(this);
                break;
            case R.id.iv_agree:
                isChecked = !isChecked;
                iv_agree.setImageResource(isChecked ? R.drawable.login_checked : R.drawable.login_unchecked);
                break;
            case R.id.iv_login_weixin:
                break;
        }
    }

    private void login() {
        MainActivity.start(this);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
