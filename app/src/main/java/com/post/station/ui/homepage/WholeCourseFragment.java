package com.post.station.ui.homepage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.post.station.R;
import com.post.station.adapter.WaitCheckOutAdapter;
import com.post.station.base.BaseFragment;
import com.post.station.model.HomeModel;
import com.post.station.response.WaitCheckOutBean;
import com.post.station.view.EmptyViewLayout;
import com.post.station.view.HeadRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//inventory_management-待出库

public class WholeCourseFragment extends BaseFragment {

    @BindView(R.id.mRecycleView)
    HeadRecycleView mRecycleView;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeModel model = new HomeModel();
    private int pageNum = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_checkout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecycleView();
        loadData();
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

    private WaitCheckOutAdapter mAdapter = new WaitCheckOutAdapter();

    private void initRecycleView() {
        mRecycleView.setOnLoadListener(o -> loadMore());
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
        mRecycleView.setAdapter(mAdapter);
    }

    private void refresh() {
        Log.e("", "WaitCheckOutFragment - refresh");
        loadData();
    }

    private void loadMore() {
        Log.e("", "WaitCheckOutFragment - loadMore");
        if (hasMore) {
            ++pageNum;
            loadData();
        }
    }

    private void hideRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
