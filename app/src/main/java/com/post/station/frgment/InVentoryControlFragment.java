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
import com.post.station.ui.manage.ReturnedPieceFragment;
import com.post.station.ui.manage.WaitCheckOutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryControlFragment extends Fragment {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private WaitCheckOutFragment waitCheckOutFragment;
    private CheckOutFragment checkOutFragment;
    private ReturnedPieceFragment pieceFragment;

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
        checkOutFragment = new CheckOutFragment();
        pieceFragment = new ReturnedPieceFragment();

        adapter.addFragment(getString(R.string.wait_checkout), waitCheckOutFragment);
        adapter.addFragment(getString(R.string.checkout), checkOutFragment);
        adapter.addFragment(getString(R.string.returned_piece), pieceFragment);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
