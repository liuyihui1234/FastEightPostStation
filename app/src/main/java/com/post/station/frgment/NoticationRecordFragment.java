package com.post.station.frgment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.post.station.R;
import com.post.station.adapter.SmsRecordAdapter;
import com.post.station.adapter.WaitCheckOutAdapter;
import com.post.station.base.BaseFragment;
import com.post.station.model.HomeModel;
import com.post.station.response.WaitCheckOutBean;
import com.post.station.utils.SpUtils;
import com.post.station.view.EmptyViewLayout;
import com.post.station.view.HeadRecycleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//短信记录
public class NoticationRecordFragment extends BaseFragment {

    @BindView(R.id.mRecycleView)
    HeadRecycleView mRecycleView;
    @BindView(R.id.mBackImageBtn)
    ImageView mBackImageBtn;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeModel model = new HomeModel();
    private int pageNum = 1;
    private boolean hasMore = false;
    private String pageSize = "10";

    private static final String ARG_PARAM1 = "param1";
    private String mParam1 = "";

    public static NoticationRecordFragment newInstance(String param1) {
        NoticationRecordFragment fragment = new NoticationRecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticationrecord, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(mParam1) && mParam1.equals("1")) {
            mBackImageBtn.setVisibility(View.VISIBLE);
        }
        initRecycleView();
        loadData();
    }

    @OnClick({R.id.mBackImageBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBackImageBtn:
                getActivity().finish();
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

    private SmsRecordAdapter mAdapter = new SmsRecordAdapter();

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
    public void onDestroyView() {
        super.onDestroyView();
    }
}