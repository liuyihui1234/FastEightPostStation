package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.post.station.R;
import com.post.station.base.BaseActivity;

public class BrandManagementaActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_brand_management);
        showAppBar(false);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, BrandManagementaActivity.class));
    }
}
