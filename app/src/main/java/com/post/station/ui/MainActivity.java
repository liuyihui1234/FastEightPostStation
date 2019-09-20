package com.post.station.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.frgment.HomeFragment;
import com.post.station.frgment.InventoryControlFragment;
import com.post.station.frgment.NoticationRecordFragment;
import com.post.station.frgment.PersonalCenterFragment;
import com.post.station.model.HomeModel;
import com.post.station.response.VersionBean;
import com.post.station.service.DownloadIntentService;
import com.post.station.utils.AppUtils;
import com.post.station.utils.PermissionsUtils;
import com.post.station.utils.TimeUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static WeakReference<MainActivity> reference = null;

    public static MainActivity getInstance() {
        return reference.get();
    }

    @BindView(R.id.iv_homepage)
    ImageView iv_homepage;
    @BindView(R.id.tv_homepage)
    TextView tv_homepage;
    @BindView(R.id.iv_order)
    ImageView iv_order;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.iv_shop)
    ImageView iv_shop;
    @BindView(R.id.tv_shop)
    TextView tv_shop;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;
    @BindView(R.id.tv_mine)
    TextView tv_mine;
    private HomeModel homeModel = new HomeModel();

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_main);
        reference = new WeakReference<>(this);
        showAppBar(false);
        useThemestatusBarColor = true;
        setStatusBar();
        ButterKnife.bind(this);
        addFragment(new HomeFragment());
        //loadVersion();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenLayout, fragment);
        transaction.commit();
    }

    @OnClick({R.id.ll_homepage, R.id.ll_order, R.id.ll_shop, R.id.ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_homepage:
                showll_bar(true);
                setButtonStatue(true, false, false, false);
                addFragment(new HomeFragment());
                break;
            case R.id.ll_order:
                showll_bar(true);
                setButtonStatue(false, true, false, false);
                addFragment(new InventoryControlFragment());
                break;
            case R.id.ll_shop:
                showll_bar(true);
                setButtonStatue(false, false, true, false);
                addFragment(new NoticationRecordFragment());
                break;
            case R.id.ll_mine:
                showll_bar(false);
                setButtonStatue(false, false, false, true);
                addFragment(new PersonalCenterFragment());
                break;
        }
    }

    public void setButtonStatue(boolean homepage, boolean order, boolean shop, boolean mine) {
        iv_homepage.setImageResource(homepage ? R.drawable.homepage_click : R.drawable.homepage);
        tv_homepage.setTextColor(homepage ? getResources().getColor(R.color.base_color) : getResources().getColor(R.color.black1));
        iv_order.setImageResource(order ? R.drawable.homepage_check_click : R.drawable.homepage_check);
        tv_order.setTextColor(order ? getResources().getColor(R.color.base_color) : getResources().getColor(R.color.black1));
        iv_shop.setImageResource(shop ? R.drawable.homepage_shop_click : R.drawable.homepage_shop);
        tv_shop.setTextColor(shop ? getResources().getColor(R.color.base_color) : getResources().getColor(R.color.black1));
        iv_mine.setImageResource(mine ? R.drawable.homepage_my_click : R.drawable.homepage_my);
        tv_mine.setTextColor(mine ? getResources().getColor(R.color.base_color) : getResources().getColor(R.color.black1));
    }

    private void loadVersion() {
        homeModel.getVersion("3", e -> {
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }, result -> {
            if (result.isSuccess() && result.getData() != null) {
                versionBean = result.getData();
                upload();
            }
        });
    }

    private VersionBean versionBean;
    private String downloadurl = "";
    private String version = "";

    private void upload() {
        if (versionBean == null)
            return;
        String version = versionBean.getVersion();
        String currentVersion = AppUtils.getPackageName(this);
        //当前版本小于服务器版本就更新
        if (AppUtils.compareVersion(version, currentVersion) == 1) {
            //当前时间大于等于服务器的有效时间就更新
            String nowTime = TimeUtils.getStringTime(System.currentTimeMillis());
            String endTime = versionBean.getValidtime();
            if (TimeUtils.timeCompare(nowTime, endTime)) {
                this.downloadurl = versionBean.getDownloadurl();
                this.version = version;
                if (versionBean.getForceUpdateFlage() == 1) {//强制更新
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (!PermissionsUtils.isNotificationEnabled(this)) {
                            toast("请设置通知栏权限");
                            PermissionsUtils.gotoNotificationSetting(this, REQUEST_SETTING_NOTIFICATION);
                        } else {
                            startServices();
                        }
                    }
                } else showUpdataDialog();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 弹出对话框
     */
    protected void showUpdataDialog() {
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setTitle("版本升级");
        builer.setMessage("软件更新");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", (dialog, which) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (!PermissionsUtils.isNotificationEnabled(this)) {
                    toast("请设置通知栏权限");
                    PermissionsUtils.gotoNotificationSetting(this, REQUEST_SETTING_NOTIFICATION);
                } else {
                    startServices();
                }
            }
        });
        //当点取消按钮时不做任何举动
        builer.setNegativeButton("取消", (dialogInterface, i) -> {
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //....自行做回调处理
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SETTING_NOTIFICATION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (PermissionsUtils.isNotificationEnabled(this)) {
                    startServices();
                } else {
                    toast("通知栏权限未申请");
                }
            }
        }
    }

    private static final int REQUEST_SETTING_NOTIFICATION = 1;

    private void startServices() {
        Toast.makeText(this, "" + "开始下载", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.INTENT_VERSION_NAME, version);
        intent.putExtra(DownloadIntentService.INTENT_DOWNLOAD_URL, downloadurl);
        startService(intent);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                toast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
