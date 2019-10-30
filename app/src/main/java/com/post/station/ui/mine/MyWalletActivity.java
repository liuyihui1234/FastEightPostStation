package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.CapitalDetailActivity;
import com.post.station.CapitalDetailsActivity;
import com.post.station.R;
import com.post.station.SetUpActivity;
import com.post.station.base.BaseActivity;
import com.post.station.ui.homepage.InformActivity;
import com.post.station.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity {
//mBackImageBtn tv_capitalDetails
@BindView(R.id.mBackImageBtn)
ImageView mBackImageBtn;
    @BindView(R.id.tv_capitalDetails)
    TextView tv_capitalDetails;


    public static void start(Context context) {
        context.startActivity(new Intent(context, MyWalletActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
//        showAppBar(false);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.mBackImageBtn,R.id.tv_capitalDetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.tv_capitalDetails:
                startActivity(new Intent(this, CapitalDetailActivity.class));
                break;
        }
    }

}
