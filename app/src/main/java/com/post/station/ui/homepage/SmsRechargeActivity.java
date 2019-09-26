package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.widget.SmsSettingPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsRechargeActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SmsRechargeActivity.class));
    }

    @BindView(R.id.tv_setting)
    TextView tv_setting;
    @BindView(R.id.ll_postStation)
    LinearLayout ll_postStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_send_sms);
        ButterKnife.bind(this);
        setContentTitle("云呼");
        showRightText(true, "发送");
        showBackButton(true);
        ll_postStation.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_setting, R.id.ll_start_template})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:
                showSettingPopWindow();
                break;
            case R.id.ll_start_template:
                SmsTemplateActivity.start(this, 1);
                break;
        }
    }

    private SmsSettingPopupWindow popupWindow;

    private void showSettingPopWindow() {
        popupWindow = new SmsSettingPopupWindow(this);
        popupWindow.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
            popupWindow = null;
        }
    }
}
