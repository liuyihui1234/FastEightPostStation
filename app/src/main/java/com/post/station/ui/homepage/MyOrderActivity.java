package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.post.station.R;
import com.post.station.adapter.TabDetectionAdapter;
import com.post.station.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    Unbinder unbinder;

    private UnCompleteFragment unCompleteFragment;
    private CompleteFragment completeFragment;
    private AlreadyPrintedFragment printedFragment;
    private WholeCourseFragment courseFragment;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MyOrderActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_my_order);
        setContentTitle("我的订单");
        showRightText(true, "批量打印");
        showBackButton(true);
        unbinder = ButterKnife.bind(this);

        TabDetectionAdapter adapter = new TabDetectionAdapter(getSupportFragmentManager());
        unCompleteFragment = new UnCompleteFragment();
        completeFragment = new CompleteFragment();
        printedFragment = new AlreadyPrintedFragment();
        courseFragment = new WholeCourseFragment();

        adapter.addFragment(getString(R.string.uncompleted), unCompleteFragment);
        adapter.addFragment(getString(R.string.completed), completeFragment);
        adapter.addFragment(getString(R.string.already_printed), printedFragment);
        adapter.addFragment(getString(R.string.whole_course), courseFragment);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
