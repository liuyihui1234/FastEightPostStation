package com.post.station.ui.mine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.post.station.BandAdapters;
import com.post.station.R;
import com.post.station.adapter.BandAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.response.BandBean;
import com.post.station.ui.homepage.PaymentActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrandManagementaActivity extends BaseActivity {
//btn_brandNumber
    @BindView(R.id.mBackImageBtn)
    ImageView mBackImageBtn;
    @BindView(R.id.btn_brandNumber)
    Button btn_brandNumber;
    @BindView(R.id.lv_express)
    ListView lv_express;
    List<BandBean> list = new ArrayList();
    private String str;


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
    @OnClick({R.id.mBackImageBtn,R.id.btn_brandNumber})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.btn_brandNumber:
                startActivityForResult(new Intent(this, AddExpressBrandActivity.class),1);
                init();
                break;

        }
    }
    private void init(){
        BandAdapters adapter=new BandAdapters(BrandManagementaActivity.this,list);
        lv_express.setAdapter(adapter);


    }





    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
         str=data.getStringExtra("addressBean");
        Log.e("打印","onActivityResult: "+str);
    }
}
