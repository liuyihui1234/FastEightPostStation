package com.post.station;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuotationActivity extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        listView=findViewById(R.id.listView);

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("city", "浙江、江苏、上海");
        map3.put("firstPriority", "首重8元/kg");
        map3.put("continuousWeight", "续重8元/kg");
        list.add(map3);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.item_quotation,
                new String[]{"city","firstPriority","continuousWeight"},new int[]{R.id.tv_city,R.id.tv_firstPriority,R.id.tv_continuousWeight});
        listView.setAdapter(simpleAdapter);
    }
}
