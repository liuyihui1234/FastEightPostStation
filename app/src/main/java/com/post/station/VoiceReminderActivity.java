package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoiceReminderActivity extends BaseActivity {
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.iv_close2)
    ImageView iv_close2;
    @BindView(R.id.iv_close1)
    ImageView iv_close1;
    @BindView(R.id.iv_close3)
    ImageView iv_close3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_voice_reminder);
        setContentTitle("声音设置");
        ButterKnife.bind(this);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, VoiceReminderActivity.class));
    }
    private boolean derail = true;
    private boolean derai2 = true;
    private boolean derai3 = true;
    private boolean derai4 = true;
    @OnClick({R.id.iv_close,R.id.iv_close1,R.id.iv_close2,R.id.iv_close3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                derail = !derail;
                iv_close.setImageResource(derail ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
            case R.id.iv_close1:
                derai2= !derai2;
                iv_close1.setImageResource(derai2 ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
            case R.id.iv_close2:
                derai3= !derai3;
                iv_close2.setImageResource(derai3 ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
            case R.id.iv_close3:
                derai4= !derai4;
                iv_close3.setImageResource(derai4 ? R.drawable.ordersetting : R.drawable.ordersetting1);
                break;
        }
    }
}
