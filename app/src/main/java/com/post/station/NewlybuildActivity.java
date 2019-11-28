package com.post.station;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.post.station.base.BaseActivity;
import com.post.station.ui.homepage.SmsHelpActivity;
import com.post.station.ui.mine.MyWalletActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewlybuildActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlybuild);
        ButterKnife.bind(this);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, NewlybuildActivity.class));
    }
    @OnClick({R.id.mBackImageBtn,R.id.tv_notice,R.id.tv_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.tv_notice:
                NoticeActivity.start(this);
                break;
            case R.id.tv_help:
                SmsHelpActivity.start(this);
                break;
        }
    }
}
