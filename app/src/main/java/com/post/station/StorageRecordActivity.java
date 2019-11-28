package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.post.station.adapter.BandsAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.response.BandBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StorageRecordActivity extends BaseActivity {
    private ListView lv_record;
    private String touserSignature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_record);
        ButterKnife.bind(this);
        initView();
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, StorageRecordActivity.class));
    }
    @OnClick({R.id.mBackImageBtn,R.id.tv_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
            case R.id.tv_screen:
                showPopupWindow();
                break;
        }
    }
    private void initView() {
        lv_record=findViewById(R.id.lv_record);
        List<StorageRecordBean> list=new ArrayList<>();
        for (int n=0;n<10;n++){
            StorageRecordBean bean=new StorageRecordBean();
            //写活
//            bean.setNotice("关于快8驿站系统维护的通知"+n);
//            bean.setContent("本院于12月1号组织学生入院培训会议，本院于12月1号组织学生入院培训会议");
//            bean.setTime("2019-06-13 14: 30"+n);
            list.add(bean);
        }
        StorageRecordAdapter adapter=new StorageRecordAdapter(this,list);
        lv_record.setAdapter(adapter);
        lv_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adapter.setonclick(position);
                //adapter.notifyDataSetChanged();
                adapter.setSelectFlag(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private StorageRecordPopupWindow popupWindow;

    private void showPopupWindow() {
        if (popupWindow != null) {
            popupWindow = null;
        }
        popupWindow = new StorageRecordPopupWindow(this,touserSignature -> {
        },touserSignature -> {
            this.touserSignature = touserSignature;

        });

        popupWindow.show();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow = null;
        }
    }
}
