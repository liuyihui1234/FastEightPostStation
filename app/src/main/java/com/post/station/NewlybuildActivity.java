package com.post.station;

import android.os.Bundle;
import com.post.station.base.BaseActivity;

public class NewlybuildActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_newlybuild);
        showAppBar(false);
    }
}
