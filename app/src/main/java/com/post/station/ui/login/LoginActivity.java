package com.post.station.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.post.station.ui.MainActivity;
import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_agree)
    ImageView iv_agree;
    @BindView(R.id.iv_eye)
    ImageView iv_eye;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_password)
    EditText et_password;
    Unbinder unbinder;

    public static void start(Context context) {
        context.startActivity(new Intent(context,LoginActivity.class));
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
    private boolean isChecked1 = false;
    @OnClick({R.id.rl_login,R.id.tvForgetPwd,R.id.iv_login_weixin,
            R.id.tv_new_user,R.id.iv_agree,R.id.iv_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgetPwd:
                FoundPasswordActivity.start(this);
                break;
            case R.id.rl_login:
                if (isChecked){
                    //isChecked 输入框里面的判断默认为true
                    login();
                }else {
                    Toast.makeText(this,"请同意条款",Toast.LENGTH_SHORT).show();
                }

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
            case R.id.iv_eye:
                iv_eye.setImageResource(isChecked1 ? R.drawable.grey_eye : R.drawable.order_eye);
                if (!isChecked1) {
                    isChecked1 = true;
                    //如果选中，显示密码
                    et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    isChecked1 = false;
                    //否则隐藏密码
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                    break;
        }
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(et_pwd.getText().toString())) {
            toast("请输入手机号");
            return true;
        }
        if (TextUtils.isEmpty(et_password.getText().toString())) {
            toast("请输入密码");
            return true;
        }
        return false;
    }
    private void login() {
        if (isEmpty()) return;
        finish();
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
