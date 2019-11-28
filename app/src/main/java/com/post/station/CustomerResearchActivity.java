package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.AddCustomerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerResearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_research);
        ButterKnife.bind(this);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, CustomerResearchActivity.class));
    }
    @OnClick({R.id.mBackImageBtn,R.id.iv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.iv_more:
                AddCustomerActivity.start(this);
                break;
        }
    }
}
