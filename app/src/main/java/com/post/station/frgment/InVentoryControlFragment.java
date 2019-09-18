package com.post.station.frgment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.post.station.R;
import com.post.station.adapter.TabDetectionAdapter;
import com.post.station.model.HomeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryControlFragment extends Fragment {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private HomeModel model = new HomeModel();
    private WaitCheckOutFragment waitCheckOutFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_control, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabDetectionAdapter adapter = new TabDetectionAdapter(getChildFragmentManager());
        waitCheckOutFragment = new WaitCheckOutFragment();

        adapter.addFragment(getString(R.string.wait_checkout), waitCheckOutFragment);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
