package com.post.station.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.post.station.BrandManagementAdapter;
import com.post.station.R;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrandManagementActivity extends BaseActivity {
//btn_brandNumber
    @BindView(R.id.mBackImageBtn)
    ImageView mBackImageBtn;
    @BindView(R.id.btn_brandNumber)
    Button btn_brandNumber;
    @BindView(R.id.lv_express)
    ListView lv_express;
//    List<BrandManagement> list = new ArrayList();
    BrandManagementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_management);
//        showAppBar(false);
        ButterKnife.bind(this);
        initView();
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, BrandManagementActivity.class));
    }
    @OnClick({R.id.mBackImageBtn,R.id.btn_brandNumber})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.btn_brandNumber:
                startActivityForResult(new Intent(this, AddExpressBrandActivity.class),1);
                break;
        }
    }
    private void initView() {
        lv_express=findViewById(R.id.lv_express);
          adapter=new BrandManagementAdapter(this,minBean.listss);
        lv_express.setAdapter(adapter);
        lv_express.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectFlag(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Log.e("SSSSS","onActivityResult: "+minBean.listss.size());
        adapter.notifyDataSetChanged();
    }

}
