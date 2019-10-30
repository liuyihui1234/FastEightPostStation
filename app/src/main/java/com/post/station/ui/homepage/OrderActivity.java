package com.post.station.ui.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.post.station.BatchOrderActivity;
import com.post.station.CreateOrderActivity;
import com.post.station.OrderSettingActivity;
import com.post.station.R;
import com.post.station.SweepOrderActivity;
import com.post.station.base.BaseActivity;
import com.post.station.zxing.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends BaseActivity {
    private GridView gview;
    private GridView gview1;
    private List<Map<String, Object>> data_list1;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private SimpleAdapter sim_adapter1;
    private int[] icon = {R.mipmap.create_order, R.mipmap.scan_order,
            R.mipmap.batch_order, R.mipmap.place_order};
    private String[] iconName= {"创建订单",  "扫描下单", "批量下单", "邀请下单"};
    private int[] icon1 = {R.mipmap.myorder_ic, R.mipmap.orderset_ic,
            R.mipmap.moneyrecord_ic, R.mipmap.realname_ic, R.mipmap.discountcoupon_ic,
            R.mipmap.pricebill_ic};
    private String[] iconName1 = {"我的订单",  "订单设置", "收款记录", "实名记录", "优惠券", "报价单"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        gview = (GridView) findViewById(R.id.gridview);
        gview1 = (GridView) findViewById(R.id.gridview1);
        //新建List
        data_list1 = new ArrayList<Map<String, Object>>();
        data_list = new ArrayList<Map<String, Object>>();

        //获取数据
        getData();
        getData1();
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
                        CreateOrderActivity.start(OrderActivity.this);
                        break;
                    case 1:
                        SweepOrderActivity.start(OrderActivity.this);
                        break;
                    case 2:
                        BatchOrderActivity.start(OrderActivity.this);
                        break;
                    case 3:
                        break;
                }
            }
        });
        sim_adapter1 = new SimpleAdapter(this, data_list1, R.layout.item_home_center, from, to);
        //配置适配器
        gview1.setAdapter(sim_adapter1);
        gview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        MyOrderActivity.start(OrderActivity.this);
                        break;
                    case 1:
                        OrderSettingActivity.start(OrderActivity.this);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;

                }
            }
        });
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

    public List<Map<String, Object>> getData1() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon1.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon1[i]);
            map.put("text", iconName1[i]);
            data_list1.add(map);
        }

        return data_list1;
    }
}
