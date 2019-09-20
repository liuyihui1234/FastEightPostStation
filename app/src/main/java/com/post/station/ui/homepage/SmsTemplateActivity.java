package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.post.station.R;
import com.post.station.adapter.TabDetectionAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.ui.notication_record.AuditFragment;
import com.post.station.ui.notication_record.HavePassedFragment;
import com.post.station.ui.notication_record.NotPassedFragment;
import com.post.station.ui.notication_record.ThirdPartyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SmsTemplateActivity extends BaseActivity {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    Unbinder unbinder;

    private ThirdPartyFragment partyFragment;
    private HavePassedFragment passedFragment;
    private NotPassedFragment notPassedFragment;
    private AuditFragment auditFragment;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SmsTemplateActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sms_template);
        setContentTitle("短信模板");
        showBackButton(true);
        unbinder = ButterKnife.bind(this);

        TabDetectionAdapter adapter = new TabDetectionAdapter(getSupportFragmentManager());
        partyFragment = new ThirdPartyFragment();
        passedFragment = new HavePassedFragment();
        notPassedFragment = new NotPassedFragment();
        auditFragment = new AuditFragment();

        adapter.addFragment(getString(R.string.third_party), partyFragment);
        adapter.addFragment(getString(R.string.have_passed), passedFragment);
        adapter.addFragment(getString(R.string.not_passed), notPassedFragment);
        adapter.addFragment(getString(R.string.audit), auditFragment);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
