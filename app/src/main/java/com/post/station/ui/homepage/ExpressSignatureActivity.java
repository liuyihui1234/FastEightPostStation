package com.post.station.ui.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.post.station.R;
import com.post.station.adapter.CYBChangeCityGridViewAdapter;
import com.post.station.adapter.ContactAdapter;
import com.post.station.base.BaseActivity;
import com.post.station.response.UserEntity;
import com.post.station.view.NoScrollGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * created by zw
 * 2019/9/25
 * 快递签名/自定义
 */
public class ExpressSignatureActivity extends BaseActivity {

    @BindView(R.id.tv_express_sign)
    TextView tv_express_sign;
    @BindView(R.id.tv_custom)
    TextView tv_custom;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ExpressSignatureActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_express_signture);
        ButterKnife.bind(this);
        showAppBar(false);
        addFragment(new ExpressSignatureFragment());
    }

    private boolean isExpressSign = true;

    @OnClick({R.id.tv_express_sign, R.id.tv_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_express_sign:
                isExpressSign = true;
                tv_express_sign.setBackgroundResource(isExpressSign ? R.drawable.corner_3_white_drawable : R.drawable.corner_3_red_drawable);
                tv_custom.setBackgroundResource(!isExpressSign ? R.drawable.corner_3_white_drawable : R.drawable.corner_3_red_drawable);
                tv_express_sign.setTextColor(getResources().getColor(R.color.base_color));
                tv_custom.setTextColor(getResources().getColor(R.color.white));
                tv_express_sign.setText("快递签名");
                tv_custom.setText("自定义");
                addFragment(new ExpressSignatureFragment());
                break;
            case R.id.tv_custom:
                isExpressSign = !isExpressSign;
                tv_express_sign.setBackgroundResource(isExpressSign ? R.drawable.corner_3_white_drawable : R.drawable.corner_3_red_drawable);
                tv_custom.setBackgroundResource(!isExpressSign ? R.drawable.corner_3_white_drawable : R.drawable.corner_3_red_drawable);
                tv_express_sign.setTextColor(getResources().getColor(R.color.white));
                tv_custom.setTextColor(getResources().getColor(R.color.base_color));
                tv_express_sign.setText("快递签名");
                tv_custom.setText("自定义");
                addFragment(new CustomFragment());
                break;
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenLayout, fragment);
        transaction.commit();
    }
}
