package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.iv_img)
    ImageView iv_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        setContentTitle("我要收款");
    }

    @OnClick({R.id.ll_zfb, R.id.ll_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zfb:
                iv_img.setImageResource(R.mipmap.zfb_ic);
                break;
            case R.id.ll_wx:
                iv_img.setImageResource(R.mipmap.wx_ic);
                break;
        }}
    public static void start(Context context) {
        context.startActivity(new Intent(context, PaymentActivity.class));
    }
}
