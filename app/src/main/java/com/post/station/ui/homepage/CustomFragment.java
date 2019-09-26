package com.post.station.ui.homepage;

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
import com.post.station.ui.manage.CheckOutFragment;
import com.post.station.ui.manage.ReturnedPieceFragment;
import com.post.station.ui.manage.WaitCheckOutFragment;
import com.post.station.ui.notication_record.AuditFragment;
import com.post.station.ui.notication_record.HavePassedFragment;
import com.post.station.ui.notication_record.NotPassedFragment;
import com.post.station.ui.notication_record.ThirdPartyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomFragment extends Fragment {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private HavePassedFragment passedFragment;
    private NotPassedFragment notPassedFragment;
    private AuditFragment auditFragment;

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
        passedFragment = new HavePassedFragment();
        notPassedFragment = new NotPassedFragment();
        auditFragment = new AuditFragment();

        adapter.addFragment(getString(R.string.have_passed), passedFragment);
        adapter.addFragment(getString(R.string.not_passed), notPassedFragment);
        adapter.addFragment(getString(R.string.audit), auditFragment);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
