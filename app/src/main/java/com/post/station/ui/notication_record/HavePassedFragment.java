package com.post.station.ui.notication_record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.post.station.AdoptAdapter;
import com.post.station.NewlybuildActivity;
import com.post.station.OrderSettingActivity;
import com.post.station.R;
import com.post.station.TemplateAdapter;
import com.post.station.TemplateBean;
import com.post.station.adapter.WaitCheckOutAdapter;
import com.post.station.base.BaseFragment;
import com.post.station.model.HomeModel;
import com.post.station.response.WaitCheckOutBean;
import com.post.station.utils.SpUtils;
import com.post.station.view.EmptyViewLayout;
import com.post.station.view.HeadRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//已通过

public class HavePassedFragment extends BaseFragment {

    @BindView(R.id.mRecycleView)
    HeadRecycleView mRecycleView;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.ll_new_template)
    LinearLayout ll_new_template;
    private HomeModel model = new HomeModel();
    private int pageNum = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_checkout, container, false);
        ButterKnife.bind(this, view);
        ll_new_template.setVisibility(View.VISIBLE);
        return view;
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, HavePassedFragment.class));
    }
    @OnClick({R.id.ll_new_template})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_new_template:
                NewlybuildActivity.start(getActivity());
                break;
        }
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
//    }
        List<TemplateBean> orderBeans = new ArrayList<>();
        emptyView.setVisibility(View.GONE);
        for (int i = 0; i < 10; i++) {
            TemplateBean templateBean = new TemplateBean();
            templateBean.address = "" + i;
            templateBean.code = "" + i;
            templateBean.telephone = "" + i;
            orderBeans.add(templateBean);
        }
        mAdapter.setItems(orderBeans);
    }

    private AdoptAdapter mAdapter = new AdoptAdapter();
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
