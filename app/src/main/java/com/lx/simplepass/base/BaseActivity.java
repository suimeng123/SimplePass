package com.lx.simplepass.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.library.http.httputil.OkHttp3Util;
import com.base.library.http.parser.BaseParser;
import com.base.library.utils.MMKVUtil;
import com.lx.simplepass.parser.MyParser;
import com.lx.simplepass.utils.ScreenAdapterUtil;

/**
 * com.lx.simplepass.base
 * SimplePass
 * Created by lixiao2
 * 2019/1/9.
 */

public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    public OkHttp3Util http3Util;
    public BaseParser parser;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ScreenAdapterUtil.setCustomDesnsity(APPApplication.getApplication(), this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            // 隐藏标题栏
            getSupportActionBar().hide();
        }
        mContext = this;

        /** 初始化网络请求解析器 可以自定义 **/
        parser = new MyParser();
        /** 初始化okhttp3网络请求工具 **/
        http3Util = new OkHttp3Util(mHandler, parser, null);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initLayout();
        initData();
    }

    /** 网络请求handler **/
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            resultData(msg.what, msg.getData());
        }
    };

    /** 处理网络请求返回数据 **/
    public void resultData(int tag, Bundle bundle){};
    /** 初始化布局 **/
    public void initLayout(){};
    /** 初始化数据 **/
    public void initData(){};
}
