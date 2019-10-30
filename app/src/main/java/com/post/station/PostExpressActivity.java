package com.post.station;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.post.station.adapter.BandsAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.response.BandBean;
import com.post.station.response.BandBeans;
import com.post.station.ui.homepage.OrderActivity;
import com.post.station.ui.mine.AddExpressBrandActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostExpressActivity extends BaseActivity {
private ListView lv_expressNews;
    private List<Map<String, Object>> data_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_post_express);
        setContentTitle("驿站快报");
        initView();

    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, PostExpressActivity.class));
    }
    private void initView() {
        lv_expressNews=findViewById(R.id.lv_expressNews);
        List<BandBeans> list=new ArrayList<>();
        for (int n=0;n<10;n++){
            BandBeans bean=new BandBeans();
            //写活
//            bean.setNotice("关于快8驿站系统维护的通知"+n);
//            bean.setContent("本院于12月1号组织学生入院培训会议，本院于12月1号组织学生入院培训会议");
//            bean.setTime("2019-06-13 14: 30"+n);
            list.add(bean);
        }
        BandsAdapter adapter=new BandsAdapter(PostExpressActivity.this,list);
        lv_expressNews.setAdapter(adapter);
        lv_expressNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adapter.setonclick(position);
                //adapter.notifyDataSetChanged();
                adapter.setSelectFlag(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
