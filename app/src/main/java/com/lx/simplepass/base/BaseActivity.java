package com.lx.simplepass.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.base.library.http.httputil.OkHttp3Util;
import com.base.library.http.parser.BaseParser;
import com.lx.simplepass.parser.MyParser;

/**
 * com.lx.simplepass.base
 * SimplePass
 * Created by lixiao2
 * 2019/1/9.
 */

public class BaseActivity extends BaseTitleActivity {
    public OkHttp3Util http3Util;
    public BaseParser parser;

    private CheckPermissFragment permissFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
