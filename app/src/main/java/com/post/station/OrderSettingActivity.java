package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.post.station.base.BaseActivity;
import com.post.station.ui.login.FoundPasswordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderSettingActivity extends BaseActivity {
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.iv_close2)
    ImageView iv_close2;
    @BindView(R.id.iv_open)
    ImageView iv_open;
    @BindView(R.id.iv_open2)
    ImageView iv_open2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_order_setting);
        setContentTitle("订单设置");
        ButterKnife.bind(this);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, OrderSettingActivity.class));
    }
    private boolean derail = true;
    private boolean derai2 = true;
    private boolean derai3 = false;
    private boolean derai4 = false;
    @OnClick({R.id.iv_close,R.id.iv_close2,R.id.iv_open,R.id.iv_open2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                derail = !derail;
                iv_close.setImageResource(derail ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
            case R.id.iv_close2:
                derai2= !derai2;
                iv_close2.setImageResource(derai2 ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
            case R.id.iv_open:
                derai3= !derai3;
                iv_open.setImageResource(derai3 ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
            case R.id.iv_open2:
                derai4= !derai4;
                iv_open2.setImageResource(derai4 ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
        }
    }
}
