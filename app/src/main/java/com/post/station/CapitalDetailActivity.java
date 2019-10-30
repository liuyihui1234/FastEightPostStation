package com.post.station;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.post.station.adapter.BandAdapter;
import com.post.station.adapter.BandsAdapter;
import com.post.station.adapter.BandssAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.response.BandBeans;
import com.post.station.response.PenaltyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CapitalDetailActivity extends BaseActivity {
    @BindView(R.id.mRecycleView)
    ListView mRecycleView;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_capital_detail);
        setContentTitle("资金明细");
        ButterKnife.bind(this);
       initRecycleView();
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, CapitalDetailActivity.class));
    }
    @OnClick({R.id.iv_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_screen:
                startActivity(new Intent(this, CapitalDetailsActivity.class));
                break;
        }
    }

    private void initRecycleView() {
        List<PenaltyBean> list = new ArrayList<>();
        for (int n = 0; n < 10; n++) {
            PenaltyBean bean = new PenaltyBean();
            bean.setBenefits("");
            bean.setBenefitsplace("");
            bean.setDate("");
            list.add(bean);
        }
        BandssAdapter adapter = new BandssAdapter(CapitalDetailActivity.this,list);
        mRecycleView.setAdapter(adapter);
        mRecycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
