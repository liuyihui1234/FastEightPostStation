package com.post.station.ui.mine;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.hb.dialog.myDialog.ActionSheetDialog;
import com.post.station.CapitalDetailsActivity;
import com.post.station.R;
import com.post.station.base.BaseActivity;
import com.post.station.frgment.PersonalCenterFragment;
import com.post.station.glide.GlideUtils;
import com.post.station.utils.BitMapUtils;
import com.post.station.utils.FileUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建时间：2019/9/1814:57
 *  作者：wpx
 *  描述：
 */public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.iv_addPictures)
    ImageView iv_addPictures;
    private String identityfontpath;
    private String[] permisions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    public static void start(Context context) {
        context.startActivity(new Intent(context, FeedBackActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_feedback);
        setContentTitle("意见反馈");
        ButterKnife.bind(this);
        GlideUtils.showImageView(this, R.drawable.mywallet_feedback, "", iv_addPictures);

//        showAppBar(false);
    }
    @OnClick({R.id.tv_submission,R.id.iv_addPictures})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submission:
              finish();
                toast("反馈成功");
                break;
            case R.id.iv_addPictures:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于Android 6.0
                    if (hasPermission(permisions))
                        showPopupWindow();
                    else requestPermission(HAVE_CAMERA_PERMISSION,permisions);
                } else showPopupWindow();
                break;
        }
    }
    public static final int TAKE_PHOTO_CODE = 1;
    public static final int CHOOSE_PHOTO_CODE = 2;
    public static final int HAVE_CAMERA_PERMISSION = 4;

    private Bitmap takePhotobm = null;
    private String imgValue = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//获取系统照片上传
            switch (requestCode) {
                case TAKE_PHOTO_CODE:
                    try {
                        takePhotobm = BitMapUtils.getBitmapFormUri(mUri, this);
                        if (takePhotobm != null) savePicture();
                        Log.e("imgValue", "imgValue:= "+iv_addPictures );

                            identityfontpath=BitMapUtils.bitmaptoString(takePhotobm, 100);
                        //   imgValue = BitMapUtils.bitmaptoString(takePhotobm, 100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case CHOOSE_PHOTO_CODE:
                    Uri uri = data.getData();
                    String choosePath = FileUtil.getFilePathByUri(this, uri);
                    if (!TextUtils.isEmpty(choosePath)) {
                        //   SpUtils.saveAvatarUrl(choosePath);
                        GlideUtils.showImageView(this, R.drawable.default_address, choosePath, iv_addPictures);
                    }
                    Bitmap bitmapFormUri = BitMapUtils.getBitmapFormUri(uri, this);
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
            Toast.makeText(this, "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPopupWindow() {
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder().setTitle("请选择")
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
        String fileName = getFilesDir() + File.separator + "images" + File.separator + format + ".jpg";
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
            GlideUtils.showImageView(this, R.drawable.default_address, fileName, iv_addPictures);
            // SpUtils.saveAvatarUrl(fileName);
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
        String fileName = getFilesDir() + File.separator + "images" + File.separator + "modeify.jpg";
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
                mUri = FileProvider.getUriForFile(this, "com.post.station.fileprovider", file);
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
}
