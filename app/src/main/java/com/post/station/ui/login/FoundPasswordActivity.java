package com.post.station.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.model.HomeModel;
import com.post.station.utils.AppUtils;
import com.post.station.utils.SpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoundPasswordActivity extends BaseActivity {

    private HomeModel model = new HomeModel();

    @BindView(R.id.rl_login)
    RelativeLayout rl_login;
    @BindView(R.id.tv_get_vertication)
    TextView tv_get_vertication;
    @BindView(R.id.et_mobile)
    EditText et_mobile;
    @BindView(R.id.et_get_vertication)
    EditText et_get_vertication;
    @BindView(R.id.et_new_pwd)
    EditText et_new_pwd;

    public static void start(Context context) {
        context.startActivity(new Intent(context, FoundPasswordActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_found_password);
        setContentTitle("找回密码");
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_login, R.id.tv_get_vertication})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_login:
                upLoadPwd();
                break;
            case R.id.tv_get_vertication:
                getVerification();
                break;
        }
    }

    private void upLoadPwd() {
        String mobile = et_mobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            toast("请填写手机号");
            return;
        }
        String vertication = et_get_vertication.getText().toString().trim();
        if (TextUtils.isEmpty(vertication)) {
            toast("请填写验证码");
            return;
        }
        String new_pwd = et_new_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            toast("请填新密码");
            return;
        }
        model.modifyPwd(mobile, vertication, new_pwd, e -> {
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }, result -> {
            if (result.isSuccess()) {
                int code = result.getCode();
                if (code == 1) {
                    toast(result.getMsg());
                    LoginActivity.start(this);
                } else {
                    toast(result.getMsg());
                }
            }
            toast(result.getMsg());
        });
    }

    private void getVerification() {
        String userId = SpUtils.getUserId();
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        String mobile = et_mobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            toast("请填写手机号");
            return;
        }
        model.getForgetVerification(mobile, e -> {
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }, result -> {
            if (result.isSuccess()) {
                if (result.getData() != null) {
                    String data = result.getData();
                    if (!TextUtils.isEmpty(data))
                        timer.start();
                }
            }
            toast(result.getMsg());
        });
    }

    /**
     * 倒计时60秒，一次1秒
     */
    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            SimpleDateFormat dateFormat = new SimpleDateFormat("ss秒");
            //String time = AppUtils.timeParse(millisUntilFinished);
            AppUtils.setTexts(tv_get_vertication, dateFormat.format(new Date(millisUntilFinished)));
        }

        @Override
        public void onFinish() {
            AppUtils.setTexts(tv_get_vertication, "重新获取");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
