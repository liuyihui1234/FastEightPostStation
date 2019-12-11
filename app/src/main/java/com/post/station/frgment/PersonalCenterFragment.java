package com.post.station.frgment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.hb.dialog.myDialog.ActionSheetDialog;
import com.post.station.BusinessCardActivity;
import com.post.station.CustomerResearchActivity;
import com.post.station.PostExpressActivity;
import com.post.station.R;
import com.post.station.SetUpActivity;
import com.post.station.SiteInformationActivity;
import com.post.station.glide.GlideUtils;
import com.post.station.model.HomeModel;
import com.post.station.response.UserInfo;
import com.post.station.response.VersionBean;
import com.post.station.service.DownloadIntentService;
import com.post.station.ui.homepage.LinechartActivity;
import com.post.station.ui.mine.BrandManagementActivity;
import com.post.station.ui.mine.FeedBackActivity;
import com.post.station.ui.mine.MyWalletActivity;
import com.post.station.utils.AppUtils;
import com.post.station.utils.BitMapUtils;
import com.post.station.utils.FileUtil;
import com.post.station.utils.PermissionsUtils;
import com.post.station.utils.SpUtils;
import com.post.station.utils.TimeUtils;
import com.post.station.widget.SharePopupWindow;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import static android.app.Activity.RESULT_OK;

public class PersonalCenterFragment extends Fragment {

    private HomeModel model = new HomeModel();
    @BindView(R.id.ll_bar)
    LinearLayout ll_bar;
    @BindView(R.id.iv_portrait)
    ImageView iv_portrait;
    private String identityfontpath;
    private String identitybackpath;
    private String[] permisions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showShareView();
        Observable.timer(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> showShareView());
        initAvatar();
    }
    private void initAvatar() {
        UserInfo user_infor = FileUtil.getUser(getActivity());
        if (user_infor == null) {
            return;
        }
        GlideUtils.showImageViewToCircle(getActivity(), R.drawable.default_address, user_infor.portraitpath, iv_portrait);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setStatusBar();
    }



    @OnClick({R.id.ll_wallet,R.id.ll_TeamManagement,R.id.ll_feedback,R.id.ll_install,R.id.ll_customerManagement,R.id.ll_Post_express,R.id.iv_business_card
    ,R.id.iv_portrait,R.id.ll_my_dispatch,R.id.ll_Statistics})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_dispatch:
                SiteInformationActivity.start(getContext());
                break;
            case R.id.ll_Statistics:
                LinechartActivity.start(getContext());
                break;
            case R.id.ll_wallet:
                MyWalletActivity.start(getContext());
                break;
            case R.id.ll_TeamManagement:
                BrandManagementActivity.start(getContext());
                break;
            case R.id.ll_feedback:
                FeedBackActivity.start(getContext());
                break;
            case R.id.ll_install:
                SetUpActivity.start(getContext());
                break;
            case R.id.ll_customerManagement:
                CustomerResearchActivity.start(getContext());
                break;
            case R.id.ll_Post_express:
                PostExpressActivity.start(getContext());
                break;
            case R.id.iv_business_card:
                BusinessCardActivity.start(getContext());
                break;
            case R.id.iv_portrait:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于Android 6.0
                    if (hasPermission(permisions))
                        showPopupWindow();
                    else requestPermission(HAVE_CAMERA_PERMISSION, permisions);
                } else showPopupWindow();
                break;

        }
    }
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(getActivity(), permissions, code);
    }
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    public static final int TAKE_PHOTO_CODE = 1;
    public static final int CHOOSE_PHOTO_CODE = 2;
    public static final int HAVE_CAMERA_PERMISSION = 4;

    private Bitmap takePhotobm = null;
    private String imgValue = "11";
    @Override
    public void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_SETTING_NOTIFICATION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (PermissionsUtils.isNotificationEnabled(getActivity())) {
                    startServices();
                } else {
                    toast("通知栏权限未申请");
                }
            }
        }
        if (resultCode == RESULT_OK) {//获取系统照片上传
            switch (requestCode) {
                case TAKE_PHOTO_CODE:
                    try {
                        takePhotobm = BitMapUtils.getBitmapFormUri(mUri, getContext());
                        if (takePhotobm != null) savePicture();
                        identityfontpath=BitMapUtils.bitmaptoString(takePhotobm, 100);
                        //   imgValue = BitMapUtils.bitmaptoString(takePhotobm, 100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case CHOOSE_PHOTO_CODE:
                    Uri uri = data.getData();
                    String choosePath = FileUtil.getFilePathByUri(getContext(), uri);
                    if (!TextUtils.isEmpty(choosePath)) {
                        //   SpUtils.saveAvatarUrl(choosePath);
                        GlideUtils.showImageViewToCircle(getContext(), R.drawable.default_address, choosePath, iv_portrait);
                    }
                    Bitmap bitmapFormUri = BitMapUtils.getBitmapFormUri(uri, getContext());
                    identityfontpath=BitMapUtils.imgToBase64("", bitmapFormUri);
                    //imgValue = BitMapUtils.imgToBase64("", bitmapFormUri);
                    break;
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allowAllPermission = false;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//被拒绝授权
                allowAllPermission = false;
                break;
            }
            allowAllPermission = true;
        }
        if (allowAllPermission) {
            switch (requestCode) {
                case HAVE_CAMERA_PERMISSION:
                    showPopupWindow();
                    break;
            }
        } else {
            Toast.makeText(getContext(), "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
        }
    }
    private void showPopupWindow() {
        ActionSheetDialog dialog = new ActionSheetDialog(getContext()).builder().setTitle("请选择")
                .addSheetItem("拍照", null, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        //拍照
                        takePhoto();
                    }
                }).addSheetItem("相册", null, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        chooseFromAlbum();
                    }
                });
        dialog.show();
    }
    private void savePicture() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = dateFormat.format(new Date());
        String fileName = File.separator + "images" + File.separator + format + ".jpg";
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();//创建文件夹
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            takePhotobm.compress(Bitmap.CompressFormat.JPEG, 80, bos);//向缓冲区压缩图片
            bos.flush();
            bos.close();
            //Toast.makeText(PersonInforActivity.this, "照片保存成功，照片保存在" + fileName + "文件之中！", Toast.LENGTH_LONG).show();
            GlideUtils.showImageViewToCircle(getContext(), R.drawable.default_address, fileName, iv_portrait);
            SpUtils.saveAvatarUrl(fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //Toast.makeText(PersonInforActivity.this, "照片保存失败！" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }



    private void chooseFromAlbum() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, CHOOSE_PHOTO_CODE);
    }

    private Uri mUri;

    private void takePhoto() {
        // 步骤一：创建存储照片的文件
        String fileName = File.separator + "images" + File.separator + "modeify.jpg";
        try {///data/user/0/com.net.point/files/images/test.jpg
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            Intent intent = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //这一句非常重要
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //步骤二：Android 7.0及以上获取文件 Uri
                mUri = FileProvider.getUriForFile(getContext(), "com.fast.express.fileprovider", file);
            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //步骤三：获取文件Uri
                mUri = Uri.fromFile(file);
            }
            //步骤四：调取系统拍照
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
            startActivityForResult(intent, TAKE_PHOTO_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (sharePopupWindow != null) {
            sharePopupWindow = null;
        }
        super.onDestroy();
    }

    private void loadVersion() {
        model.getVersion("3",e -> {
            e.printStackTrace();
            Log.e("error",e.getMessage());
        },result -> {
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
        if (AppUtils.compareVersion(version,currentVersion) == 1) {
            //当前时间大于等于服务器的有效时间就更新
            String nowTime = TimeUtils.getStringTime(System.currentTimeMillis());
            String endTime = versionBean.getValidtime();
            if (TimeUtils.timeCompare(nowTime,endTime)) {
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
        Toast.makeText(getActivity(),"" + "开始下载",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.INTENT_VERSION_NAME,version);
        intent.putExtra(DownloadIntentService.INTENT_DOWNLOAD_URL,downloadurl);
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
        builer.setPositiveButton("确定",(dialog,which) -> {
            startServices();
        });
        //当点取消按钮时不做任何举动
        builer.setNegativeButton("取消",(dialogInterface,i) -> {
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
            mDialog = ProgressDialog.show(getActivity(),"提示","正在加载中");
        }
        mDialog.show();
    }


    protected void hideProgressDialog() {
        if (mDialog == null) return;
        if (mDialog.isShowing()) mDialog.dismiss();
        mDialog = null;
    }

    protected void toast(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            //根据上面设置是否对状态栏单独设置颜色
            //getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏背景色
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setParams();
    }

    private void setParams() {
        ll_bar.setVisibility(View.VISIBLE);
        //获取到状态栏的高度
        int statusHeight = AppUtils.getStatusBarHeight(getActivity());
        //动态的设置隐藏布局的高度
        ViewGroup.LayoutParams params = ll_bar.getLayoutParams();
        params.height = statusHeight;
        ll_bar.setLayoutParams(params);
    }
    private SharePopupWindow sharePopupWindow;

    private void showShareView() {
        if (sharePopupWindow != null) {
            sharePopupWindow = null;
        }
        sharePopupWindow = new SharePopupWindow(this, i -> {
            SHARE_MEDIA share_media = null;
            if (i == 1) {
                share_media = SHARE_MEDIA.QQ;
                SharePic(getActivity(), "qqfenxiang", "分享内容", share_media, "", umShareListener);
            } else if (i == 3) {
                share_media = SHARE_MEDIA.WEIXIN;
                SharePic(getActivity(), "weixinfenxiang", "分享内容", share_media, "", umShareListener);
            } else if (i == 5) {
                share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                SharePic(getActivity(), "weixinpengyouquanfenxiang", "分享内容", share_media, "", umShareListener);
            }
        });
    }

    /**
     * 分享监听
     */
    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(getContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getContext(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getContext(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };



    public void SharePic(Activity mActivity, String title, String context, SHARE_MEDIA Sharetype, String picurl, UMShareListener umShareListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mActivity, mPermissionList, 123);
        }
        ShareAction shareAction = new ShareAction(mActivity);
        if (!TextUtils.isEmpty(picurl)) {
            final UMWeb web = new UMWeb(picurl); //切记切记 这里分享的链接必须是http开头
            web.setTitle(title);//标题
            web.setDescription(context);//描述
            shareAction.setPlatform(Sharetype)
                    .withMedia(web)
                    .setCallback(umShareListener)
                    .share();
        } else {
            Bitmap bt = BitmapFactory.decodeResource(this.getResources(), R.drawable.share_background);
            Bitmap bitmap = BitMapUtils.compressSize(bt, 2);
            UMImage image = new UMImage(getActivity(), bitmap);//分享图标
            final UMWeb web = new UMWeb(picurl); //切记切记 这里分享的链接必须是http开头
            web.setThumb(image);  //缩略图
            shareAction.setPlatform(Sharetype)
                    .withText(context)
                    .withMedia(image)
                    .setCallback(umShareListener)
                    .share();
        }
    }






}
