package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.frgment.HomeFragment;
import com.post.station.frgment.InventoryControlFragment;
import com.post.station.frgment.NoticationRecordFragment;
import com.post.station.frgment.PersonalCenterFragment;
import com.post.station.widget.SmsSettingPopupWindow;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SendSmsActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SendSmsActivity.class));
    }

    @BindView(R.id.tv_setting)
    TextView tv_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_send_sms);
        ButterKnife.bind(this);
        setContentTitle("发短信");
        showRightText(true, "发送");
        showBackButton(true);
    }

    @OnClick({R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:
                showSettingPopWindow();
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
