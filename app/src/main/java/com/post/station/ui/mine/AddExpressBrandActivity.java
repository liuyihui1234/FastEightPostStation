package com.post.station.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.post.station.R;
import com.post.station.adapter.BandAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.response.BandBean;
import com.post.station.ui.homepage.InformActivity;
import com.post.station.ui.homepage.OrderActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpressBrandActivity extends BaseActivity {
    private TextView textView;
    GridView gridview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    List<BandBean> lists = new ArrayList();


    private int[] icon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private String[] iconName = {"申通", "韵达", "百世", "天天", "EMS", "百世da", "韵达"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_express_brand);
        gridview = findViewById(R.id.gridview);
        textView = findViewById(R.id.textView);
        SpannableString spanString = new SpannableString("快递品\n牌,切勿遗漏!  !  !");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(25);
        spanString.setSpan(span, 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new ForegroundColorSpan(Color.RED), 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spanString);
        ButterKnife.bind(this);
        initView();
    }
    @OnClick({R.id.tv_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_determine:
                saveInfor();
                break;
        }
    }



    private void saveInfor() {
        //  Log.e("寄件人地址填写","" +addressBean.toString());
       /* bandBean.img =img ;
        bandBean.name =name ;*/

        //Log.e("品牌保存", "isNull:2 "+bandBean.toString() );
        Intent intent = new Intent();
        intent.putExtra("name",""+ lists);
        setResult(200, intent);
        finish();
    }

    private void initView() {

        List<BandBean> list=new ArrayList<>();
        for (int n=0;n<10;n++){
            BandBean bean=new BandBean();
            bean.setName("1"+n);
            list.add(bean);
        }
        BandAdapter adapter=new BandAdapter(AddExpressBrandActivity.this,list);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adapter.setonclick(position);
                //adapter.notifyDataSetChanged();
                BandBean bean=new BandBean();
                bean.setName(list.get(position).getName());
                bean.setImg(list.get(position).getImg());
                lists.add(bean);
                adapter.setSelectFlag(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, AddExpressBrandActivity.class));
    }
}
