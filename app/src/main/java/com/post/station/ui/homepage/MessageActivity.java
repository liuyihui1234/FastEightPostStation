package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        title.setText("消息中心");
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, MessageActivity.class));
    }


}
