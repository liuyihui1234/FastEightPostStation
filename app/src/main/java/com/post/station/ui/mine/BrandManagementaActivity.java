package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.post.station.R;

public class BrandManagementaActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_management);
        button=findViewById(R.id.btn_brandNumber);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BrandManagementaActivity.this,AddExpressBrandActivity.class);
                startActivity(intent);

            }
        });
    }
}
