package com.post.station.glide;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;
import com.post.station.R;

public class GlideImageLoader implements ImageLoader {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)    //配置上下文
                .load("file://" + path)         //设置图片路径
                .error(R.drawable.ic_default_image)  //设置错误图片
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String s, ImageView imageView, int i, int i1) {

    }

    @Override
    public void clearMemoryCache() {

    }
}
