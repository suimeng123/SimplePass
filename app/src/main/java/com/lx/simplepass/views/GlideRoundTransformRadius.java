package com.lx.simplepass.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * com.lx.simplepass.views
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

//圆角图片 可自行设置哪几个顶点是圆形
public class GlideRoundTransformRadius extends BitmapTransformation {

    private static float radius[] = new float[]{0f, 0f, 0f, 0f};
    private static float radiu = 0f;

    public GlideRoundTransformRadius(Context context, int[] dp) {
        super(context);
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 0f) {
                continue;
            }
            radiu = Resources.getSystem().getDisplayMetrics().density * dp[i];
            radius[i] = Resources.getSystem().getDisplayMetrics().density * dp[i];
        }
    }

    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {return null;}

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radiu, radiu, paint);
        if (radiu != 0) {
            if (radius[0] == 0) {
                // 左上
                canvas.drawRect(0f, 0f, radiu, radiu, paint);
            }
            if (radius[1] == 0) {
                // 右上
                canvas.drawRect(source.getWidth() - radiu, 0f, source.getWidth(), radiu, paint);
            }
            if (radius[2] == 0) {
                // 左下
                canvas.drawRect(0f, source.getHeight() - radiu, radiu, source.getHeight(), paint);
            }
            if (radius[3] == 0) {
                // 右下
                canvas.drawRect(source.getWidth() - radiu, source.getHeight() - radiu, source.getWidth(), source.getHeight(), paint);
            }
        }
        return result;
    }

    @Override public String getId() {
        return getClass().getName() + Math.round(radius[0]);
    }
}
