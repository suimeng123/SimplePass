package com.lx.simplepass.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.lx.simplepass.R;

/**
 * 图片加载工具类
 * com.lx.simplepass.utils
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class ImageLoaderUtil {

    /** 从网络加载图片 **/
    public static void loadImgForUrl(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_launcher).into(imageView);
    }

    /** 从网络加载图片 可设置圆角 或者 圆形等属性 **/
    public static void loadTransformImgForUrl(Context context, String imageUrl, ImageView imageView, BitmapTransformation transformation) {
        Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_launcher).transform(transformation).into(imageView);
    }
}
