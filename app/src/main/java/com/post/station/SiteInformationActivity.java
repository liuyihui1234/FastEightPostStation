package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.post.station.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiteInformationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_information);
        ButterKnife.bind(this);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, SiteInformationActivity.class));
    }
    @OnClick({R.id.mBackImageBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
        }
    }
}
