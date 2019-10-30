package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.AddExpressBrandActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BatchOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_order);
        ButterKnife.bind(this);

    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, BatchOrderActivity.class));
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
