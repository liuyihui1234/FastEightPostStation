package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.post.station.R;
import com.post.station.adapter.TabDetectionAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.frgment.NoticationRecordFragment;
import com.post.station.ui.notication_record.AuditFragment;
import com.post.station.ui.notication_record.HavePassedFragment;
import com.post.station.ui.notication_record.NotPassedFragment;
import com.post.station.ui.notication_record.ThirdPartyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SmsRecordActivity extends BaseActivity {

    @BindView(R.id.flContent)
    FrameLayout flContent;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SmsRecordActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_sms_record);
        showAppBar(false);
        ButterKnife.bind(this);
        addFragment(NoticationRecordFragment.newInstance("1"));
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.commit();
    }
}
