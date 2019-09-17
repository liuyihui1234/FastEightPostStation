package com.post.station.frgment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.post.station.PostStationApplication;
import com.post.station.R;
import com.post.station.model.HomeModel;
import com.post.station.response.VersionBean;
import com.post.station.service.DownloadIntentService;
import com.post.station.utils.AppUtils;
import com.post.station.utils.PermissionsUtils;
import com.post.station.utils.TimeUtils;


import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalCenterFragment extends Fragment {

    private HomeModel model = new HomeModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //call back by scan bluetooth printer
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SETTING_NOTIFICATION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (PermissionsUtils.isNotificationEnabled(getActivity())) {
                    startServices();
                } else {
                    toast("通知栏权限未申请");
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    private void loadVersion() {
        model.getVersion("3", e -> {
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }, result -> {
            if (result.isSuccess() && result.getData() != null) {
                versionBean = result.getData();
                upload();
            } else {
                toast("当前已是最新版本");
            }
        });
    }

    private String version = "";
    private VersionBean versionBean;
    private String downloadurl = "";

    private void upload() {
        if (versionBean == null) return;
        String version = versionBean.getVersion();
        String currentVersion = AppUtils.getPackageName(getActivity());
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
                        if (!PermissionsUtils.isNotificationEnabled(getActivity())) {
                            toast("请设置通知栏权限");
                            PermissionsUtils.gotoNotificationSetting(getActivity(),REQUEST_SETTING_NOTIFICATION);
                        } else {
                            startServices();
                        }
                    }
                } else showUpdataDialog();
            } else {
                toast("当前已是最新版本");
            }
        } else {
            toast("当前已经是最新版本");
        }
    }

    private static final int REQUEST_SETTING_NOTIFICATION = 1;

    private boolean isUpLoad = false;

    private void startServices() {
        isUpLoad = true;
        Toast.makeText(getActivity(), "" + "开始下载", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.INTENT_VERSION_NAME, version);
        intent.putExtra(DownloadIntentService.INTENT_DOWNLOAD_URL, downloadurl);
        getActivity().startService(intent);
    }

    /**
     * 弹出对话框
     */
    protected void showUpdataDialog() {
        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity());
        builer.setTitle("版本升级");
        builer.setMessage("软件更新");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", (dialog, which) -> {
            startServices();
        });
        //当点取消按钮时不做任何举动
        builer.setNegativeButton("取消", (dialogInterface, i) -> {
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private ProgressDialog mDialog;

    protected void showProgressDialog() {
        if (mDialog == null) {
            mDialog = ProgressDialog.show(getActivity(), "提示", "正在加载中");
        }
        mDialog.show();
    }

    protected void hideProgressDialog() {
        if (mDialog == null) return;
        if (mDialog.isShowing()) mDialog.dismiss();
        mDialog = null;
    }

    protected void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
