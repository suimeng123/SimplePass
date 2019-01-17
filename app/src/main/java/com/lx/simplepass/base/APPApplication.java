package com.lx.simplepass.base;

import android.app.Application;
import android.content.Context;

import com.base.library.app.BaseApplication;

/**
 * com.lx.simplepass.base
 * SimplePass
 * Created by lixiao2
 * 2019/1/9.
 */

public class APPApplication extends BaseApplication {
    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Application getApplication() {
        return mInstance;
    }
}
