package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrandManagementaActivity extends BaseActivity {

    @BindView(R.id.mBackImageBtn)
    ImageView mBackImageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_management);
//        showAppBar(false);
        ButterKnife.bind(this);

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, BrandManagementaActivity.class));
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
