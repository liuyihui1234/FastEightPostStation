package com.post.station.ui.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

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
        private int[] icon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
        private String[] iconName = {"短信记录", "云呼记录", "草稿箱", "模板", "统计", "短信VIP", "通用设置", "常用问题"};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inform);
            gview = (GridView)findViewById(R.id.gridview1);
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
        }

        public List<Map<String, Object>> getData(){
            //cion和iconName的长度是相同的，这里任选其一都可以
            for(int i=0;i<icon.length;i++){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", icon[i]);
                map.put("text", iconName[i]);
                data_list.add(map);
            }

            return data_list;
        }
    }
