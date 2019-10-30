package com.post.station.frgment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.post.station.R;
import com.post.station.model.HomeModel;
import com.post.station.ui.homepage.InformActivity;
import com.post.station.ui.homepage.LinechartActivity;
import com.post.station.ui.homepage.MessageActivity;
import com.post.station.ui.homepage.OrderActivity;
import com.post.station.ui.homepage.PaymentActivity;
import com.post.station.widget.IntegralPopupWindow;
import com.post.station.zxing.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

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
    private int[] icon = {R.mipmap.out_house_ic, R.mipmap.order_ic,
            R.mipmap.note_ic, R.mipmap.in_house_ic};
    private String[] iconName = {"出库", "订单", "通知", "入库"};
    private int[] icon1 = {R.mipmap.batchout_ic, R.mipmap.photoin_ic,
            R.mipmap.batchin_ic, R.mipmap.photoout_ic, R.mipmap.billin_ic,
            R.mipmap.billout_ic, R.mipmap.receipt_ic};
    private String[] iconName1 = {"批量入库", "拍照入库", "批量出库", "拍照出库", "单号入库", "快件退回", "我要收款"};

    private HomeModel model = new HomeModel();
    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        initView(view);

        view.findViewById(R.id.ll_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LinechartActivity.class));
            }
        });
        BannerView(view);
        return view;
    }
    @OnClick({R.id.iv_news,R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_news:
                startActivity(new Intent(getActivity(),MessageActivity.class));
                break;
            case R.id.iv_scan:
                showIntegralPop();
                break;
        }}
    private IntegralPopupWindow popupWindow;

    private void showIntegralPop() {
        if (popupWindow != null) {
            popupWindow = null;
        }
        popupWindow = new IntegralPopupWindow(getActivity());
        popupWindow.show();
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
                        startActivity(new Intent(getActivity(), CaptureActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), OrderActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), InformActivity.class));
                        break;
                    case 3:
                        break;
                }
            }
        });
        gview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), PaymentActivity.class));
                        break;



                }
            }
        });
    }


    private void BannerView(View view) {

        banner = (Banner) view.findViewById(R.id.banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("");
        list_title.add("");
        list_title.add("");
        list_title.add("");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //必须最后调用的方法，启动轮播图。
                .start();
        //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
        //轮播图的监听方法
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).error(R.mipmap.ic_launcher).into(imageView);
        }
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
