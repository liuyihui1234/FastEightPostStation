package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.post.station.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRegionalQuoteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_add_regional_quote);
        ButterKnife.bind(this);
        setContentTitle("添加区域报价");
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context,AddRegionalQuoteActivity.class));
    }

//    @OnClick({R.id.ll_add_quote})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.ll_add_quote:
//                finish();
//                break;
//        }
//    }
}
