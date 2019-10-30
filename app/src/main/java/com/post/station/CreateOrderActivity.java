package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.AddExpressBrandActivity;
import com.umeng.socialize.media.Base;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        ButterKnife.bind(this);


    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, CreateOrderActivity.class));
    }
    @OnClick({ R.id.mBackImageBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
        }
    }
}
