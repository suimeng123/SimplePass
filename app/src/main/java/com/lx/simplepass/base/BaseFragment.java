package com.lx.simplepass.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.base.library.http.httputil.OkHttp3Util;
import com.base.library.http.parser.BaseParser;
import com.base.library.utils.ReflexUtil;
import com.lx.simplepass.parser.MyParser;

/**
 * com.lx.simplepass.base
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class BaseFragment extends Fragment implements View.OnClickListener {

    public Context mContext;
    public OkHttp3Util http3Util;
    public BaseParser parser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        /** 初始化网络请求解析器 可以自定义 **/
        parser = new MyParser();
        /** 初始化okhttp3网络请求工具 **/
        http3Util = new OkHttp3Util(mHandler, parser, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
        initData();
    }

    /** 初始化布局 **/
    public void initLayout(View view){};
    /** 初始化数据 **/
    public void initData(){};

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

    /** 获取该fragment实例 **/
    public static Fragment getInstance(Class clazz, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Fragment fragment = (Fragment) ReflexUtil.getObject(clazz);
        fragment.setArguments(bundle);
        return  fragment;
    }

    public static Fragment getInstance(Class clazz) {
        return getInstance(clazz, null);
    }

    @Override
    public void onClick(View v) {

    }
}
