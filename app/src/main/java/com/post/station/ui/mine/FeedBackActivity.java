package com.post.station.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.post.station.CapitalDetailsActivity;
import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.frgment.PersonalCenterFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建时间：2019/9/1814:57
 *  作者：wpx
 *  描述：
 */public class FeedBackActivity extends BaseActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, FeedBackActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_feedback);
        setContentTitle("意见反馈");
        ButterKnife.bind(this);

//        showAppBar(false);
    }
    @OnClick({R.id.tv_submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submission:
              finish();
                toast("反馈成功");
                break;
        }
    }
}
