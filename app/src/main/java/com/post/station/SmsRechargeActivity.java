package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.post.station.base.BaseActivity;


import java.util.ArrayList;
import java.util.List;

public class SmsRechargeActivity extends BaseActivity {
private ListView lv_sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sms_recharge);
        setContentTitle("短信充值");
        initView();
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, SmsRechargeActivity.class));
    }
    private void initView() {
        lv_sms=findViewById(R.id.lv_sms);
        List<BandSms> list=new ArrayList<>();

            BandSms bean=new BandSms();
            //写活
            bean.setNumber("10000");
            bean.setPrice("490");
            bean.setUnivalent("4.3分/条");
            list.add(bean);
        BandSms bean1=new BandSms();
        //写活
        bean1.setNumber("20000");
        bean1.setPrice("960");
        bean1.setUnivalent("3.8分/条");
        list.add(bean1);
        BandSms bean2=new BandSms();
        //写活
        bean2.setNumber("50000");
        bean2.setPrice("2300");
        bean2.setUnivalent("3.4分/条");
        list.add(bean2);

        SmsAdapter adapter=new SmsAdapter(this,list);
        lv_sms.setAdapter(adapter);
        lv_sms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                //adapter.setonclick(position);
                //adapter.notifyDataSetChanged();
                adapter.setSelectFlag(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
