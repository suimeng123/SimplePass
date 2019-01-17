package com.lx.simplepass.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * com.lx.simplepass.utils
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 * 屏幕字体适配工具类
 */

public class ScreenAdapterUtil {
    /** 效果图的宽度 **/
    private final static int mesureDpi = 360;
    /** 系统的density **/
    private static float appDensity;
    /** 系统的scaleDensity **/
    private static float appScaleDensity;

    /** 适配屏幕和字体大小 **/
    public static void setCustomDesnsity(@NonNull final Application application, @NonNull final Activity activity) {
        // 获取系统的DisplayMetrics
        DisplayMetrics appMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            appDensity = appMetrics.density;
            appScaleDensity = appMetrics.scaledDensity;
            // 监听系统的字体是否变化
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        // 系统的字体大小改变了
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                        setCustomDesnsity(application, activity);
                    }
                }
                @Override
                public void onLowMemory() {
                }
            });
        }

        // 设置当前的density
        float targetDensity = appMetrics.widthPixels * 1.0f / mesureDpi;
        // 设置当前字体的scaleDensity (app和当前的要比例一样)
        float targetScaleDensity = (appScaleDensity / appDensity) * targetDensity;
        // 设置当前densityDpi
        int targetDensityDpi = (int) (targetDensity * 160);
        // 设置为系统的相关属性
        appMetrics.density = targetDensity;
        appMetrics.scaledDensity = targetScaleDensity;
        appMetrics.densityDpi = targetDensityDpi;

        // 获取activity的DisplayMetrics
        DisplayMetrics actMetrics = activity.getResources().getDisplayMetrics();
        // 设置为activity的相关属性
        actMetrics.density = targetDensity;
        actMetrics.scaledDensity = targetScaleDensity;
        actMetrics.densityDpi = targetDensityDpi;
    }

}
