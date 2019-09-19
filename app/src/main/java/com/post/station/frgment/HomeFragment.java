package com.post.station.frgment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.post.station.R;
import com.post.station.model.HomeModel;
import com.post.station.ui.homepage.InformActivity;
import com.post.station.ui.homepage.OrderActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    private GridView gview, gview1;
    private List<Map<String, Object>> data_list;
    private List<Map<String, Object>> data_list1;
    private SimpleAdapter sim_adapter;
    private SimpleAdapter sim_adapter1;
    // 图片封装为一个数组
    private int[] icon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private String[] iconName = {"出库", "订单", "通知", "入库"};
    private int[] icon1 = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private String[] iconName1 = {"批量入库", "拍照入库", "批量出库", "拍照出库", "单号入库", "快件退回", "我要收款"};

    private HomeModel model = new HomeModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gview = (GridView) view.findViewById(R.id.gridview);
        gview1 = (GridView) view.findViewById(R.id.gridview1);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //新建List
        data_list1 = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //获取数据
        getData1();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.item_home, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);

        sim_adapter1 = new SimpleAdapter(getActivity(), data_list1, R.layout.item_home_center, from, to);
        //配置适配器
        gview1.setAdapter(sim_adapter1);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:

                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), OrderActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), InformActivity.class));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
