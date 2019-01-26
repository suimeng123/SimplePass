package com.lx.simplepass.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lx.simplepass.R;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.model.WeixinPieceItem;

/**
 * com.lx.simplepass.activity
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class WeixinPieceDetailActivity extends BaseActivity {

    private WebView mWWContent;
    private WeixinPieceItem item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = getIntent().getParcelableExtra("item");
        setContentView(R.layout.activity_weixin_piece_detail);
        setTitleText(item.getTitle());
    }

    @Override
    public void initLayout() {
        mWWContent = findViewById(R.id.ww_content);
        mWWContent.setLayerType(View.LAYER_TYPE_HARDWARE,null);//开启硬件加速
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWWContent.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWWContent.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWWContent.getSettings().setBuiltInZoomControls(false); // 设置显示缩放按钮
        mWWContent.getSettings().setSupportZoom(false); // 支持缩放
        mWWContent.getSettings().setLoadWithOverviewMode(true);
        mWWContent.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存
        mWWContent.getSettings().setJavaScriptEnabled(true);
        mWWContent.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        mWWContent.getSettings().setSupportZoom(false); // 支持缩放
        mWWContent.getSettings().setDefaultTextEncodingName("utf-8"); // 编码
        mWWContent.getSettings().setUseWideViewPort(true);//关键点
        mWWContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWWContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWWContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }

    @Override
    public void initData() {
        mWWContent.loadUrl(item.getSourceUrl());
    }
}
