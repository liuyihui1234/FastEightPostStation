package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.post.station.R;
import com.post.station.adapter.SmsRecordAdapter;
import com.post.station.adapter.SmsStatisticsAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.model.HomeModel;
import com.post.station.response.WaitCheckOutBean;
import com.post.station.view.EmptyViewLayout;
import com.post.station.view.HeadRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsStatisticsActivity extends BaseActivity {

    @BindView(R.id.mRecycleView)
    HeadRecycleView mRecycleView;
    @BindView(R.id.ic_close_title)
    ImageView ic_close_title;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeModel model = new HomeModel();
    private int pageNum = 1;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SmsStatisticsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sms_statistics);
        ButterKnife.bind(this);
        setContentTitle("短信统计");
        initRecycleView();
        loadData();
    }

    @OnClick({R.id.ic_close_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_close_title:
                break;
        }
    }

    private void loadData() {
//        if (TextUtils.isEmpty(SpUtils.getIncNumber())) {
//            hasMore = false;
//            hideRefresh();
//            return;
//        }
        List<WaitCheckOutBean> orderBeans = new ArrayList<>();
        emptyView.setVisibility(View.GONE);
        for (int i = 0; i < 10; i++) {
            WaitCheckOutBean waitCheckOutBean = new WaitCheckOutBean();
            waitCheckOutBean.mobile = "" + i;
            waitCheckOutBean.numbe = "" + i;
            waitCheckOutBean.time = "" + i;
            waitCheckOutBean.type = "" + i;
            orderBeans.add(waitCheckOutBean);
        }
        mAdapter.setItems(orderBeans);
//        model.getWaitCheckOutList(SpUtils.getIncNumber(), pageSize, pageNum + "", e -> {
//                    e.printStackTrace();
//                    hideRefresh();
//                    Log.e("", e.getMessage());
//                    emptyView.setVisibility(View.VISIBLE);
//                    hasMore = false;
//                },
//                result -> {
//                    hideRefresh();
//                    if (result.getData() != null && !result.getData().isEmpty()) {
//                        List<WaitCheckOutBean> orderBeans = result.getData();
//                        if (orderBeans.size() < 10) {
//                            hasMore = false;
//                            mRecycleView.noMoreData();
//                        } else {
//                            hasMore = true;
//                            mRecycleView.hasMoreData();
//                        }
//                        if (pageNum == 1) {
//                            mAdapter.setItems(orderBeans);
//                        } else {
//                            mAdapter.addItems(orderBeans);
//                        }
//                        emptyView.setVisibility(View.GONE);
//                    } else {
//                        hasMore = false;
//                        mRecycleView.noMoreData();
//                        if (pageNum == 1) emptyView.setVisibility(View.VISIBLE);
//                    }
//                });
    }

    private SmsStatisticsAdapter mAdapter = new SmsStatisticsAdapter();

    private void initRecycleView() {
        mRecycleView.setOnLoadListener(o -> loadMore());
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
        mRecycleView.setAdapter(mAdapter);
    }

    private void refresh() {
        Log.e("", "NoticationRecordFragment - refresh");
        loadData();
    }

    private void loadMore() {
        Log.e("", "NoticationRecordFragment - loadMore");
        if (hasMore) {
            ++pageNum;
            loadData();
        }
    }

    private void hideRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
