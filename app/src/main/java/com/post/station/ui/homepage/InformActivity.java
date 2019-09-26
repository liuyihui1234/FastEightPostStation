package com.post.station.ui.homepage;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import androidx.appcompat.widget.SearchView;

import com.post.station.R;
import com.post.station.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformActivity extends BaseActivity {

    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private int[] icon = {R.drawable.sms_record, R.drawable.sms_template,
            R.drawable.draft_box, R.drawable.statistics, R.drawable.common_problem,
    };
    private String[] iconName = {"短信记录", "短信模板", "草稿箱", "统计", "常用问题"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        gview = (GridView) findViewById(R.id.gridview1);
        gview = (GridView) findViewById(R.id.gridview1);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();

        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item_home_center, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        SmsRecordActivity.start(InformActivity.this);
                        break;
                    case 1:
                        SmsTemplateActivity.start(InformActivity.this);
                        break;
                    case 2:
                        break;
                    case 3:
                        SmsStatisticsActivity.start(InformActivity.this);
                        break;
                    case 4:
                        SmsHelpActivity.start(InformActivity.this);
                        break;
                }
            }
        });
        LinearLayout ll_send_sms = findViewById(R.id.ll_send_sms);
        LinearLayout ll_sms_recharge = findViewById(R.id.ll_sms_recharge);
        ll_send_sms.setOnClickListener(v -> SendSmsActivity.start(InformActivity.this));
        ll_sms_recharge.setOnClickListener(v -> SmsRechargeActivity.start(InformActivity.this));
    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
}
