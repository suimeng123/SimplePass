package com.lx.simplepass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.base.library.utils.BarsUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.base.BaseActivity;

/**
 * com.lx.simplepass.activity
 * SimplePass
 * Created by lixiao2
 * 2019/1/9.
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BarsUtil.setStatusBarVisible(this, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(mContext, MainFramworkActivity.class));
                finish();
            }
        }, 1000);
    }
}
