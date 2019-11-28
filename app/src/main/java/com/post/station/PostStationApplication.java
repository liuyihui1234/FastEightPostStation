package com.post.station;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.post.station.glide.GlideImageLoader;
import com.post.station.utils.ConstantUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.lang.ref.WeakReference;

public class PostStationApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        reference = new WeakReference<>(this);
        initImagePicker();
        UMConfigure.setLogEnabled(true);
        //微信
        PlatformConfig.setWeixin(ConstantUtil.WX_APP_ID, ConstantUtil.WX_APP_KEY);
        //新浪微博(第三个参数为回调地址)
//        PlatformConfig.setSinaWeibo("3111100954", "04b48b094faeb16683c32111124ebdad",
//                "http://sns.whalecloud.com/sina2/callback");
        //qq开放平台  APP ID  APP KEY
        PlatformConfig.setQQZone(ConstantUtil.QQ_APP_ID, ConstantUtil.QQ_APP_KEY);
    }

    private static WeakReference<Context> reference = null;

    public static Context getInstance() {
        return reference.get();
    }


    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形 区域保存
        imagePicker.setSelectLimit(3);              //选中数量限制
        imagePicker.setMultiMode(true);                      //多选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(1000);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1000);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

}
