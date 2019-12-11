package com.post.station.ui.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.post.station.R;
import com.post.station.SiteInformationActivity;
import com.post.station.base.BaseActivity;
import com.post.station.ui.mine.MyWalletActivity;
import com.post.station.utils.ChartView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LinechartActivity extends BaseActivity {
private TextView mTitle;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        ButterKnife.bind(this);
        mTitle=findViewById(R.id.mTitle);


        for (int i = 0; i < 12; i++) {
            xValue.add((i + 1) + "日");
            value.put((i + 1) + "日", (int) (Math.random() * 181 + 60));//60--240
        }

        for (int i = 0; i < 6; i++) {
            yValue.add(i * 60);
        }

        ChartView chartView = (ChartView) findViewById(R.id.chartview);
        chartView.setValue(value, xValue, yValue);
        ChartView chartView1 = (ChartView) findViewById(R.id.chartview1);
        chartView1.setValue(value, xValue, yValue);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, LinechartActivity.class));
    }
    @OnClick({R.id.mBackImageBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                finish();
                break;
        }
    }

}
