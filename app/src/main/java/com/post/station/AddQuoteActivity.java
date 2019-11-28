package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.post.station.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQuoteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_add_quote);
        ButterKnife.bind(this);
        setContentTitle("报价单");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, AddQuoteActivity.class));
    }
    @OnClick({R.id.ll_add_quote})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_add_quote:
                NewBuiltActivity.start(this);
                break;
        }
    }

}
