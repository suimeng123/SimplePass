package com.lx.simplepass.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.simplepass.R;
import com.lx.simplepass.utils.ScreenAdapterUtil;

/**
 * com.lx.simplepass.base
 * SimplePass
 * Created by lixiao2
 * 2019/1/18.
 */

public class BaseTitleActivity extends AppCompatActivity {
    private View header;
    public Context mContext;
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
    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout layout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(params);
        View content = LayoutInflater.from(mContext).inflate(layoutResID,layout, false);
        header = LayoutInflater.from(mContext).inflate(R.layout.header_common_layout, null);
        header.setVisibility(View.GONE);
        layout.addView(header);
        layout.addView(content);
        setContentView(layout);
    }

    public void setHeaderVisiable(boolean isShow) {
        if (header != null) {
            if (isShow) {
                header.setVisibility(View.VISIBLE);
            } else {
                header.setVisibility(View.GONE);
            }
        }
    }

    public void setTitleText(CharSequence title) {
        if (header != null) {
            ((TextView)header.findViewById(R.id.header_txt_title)).setText(title);
        }
    }
}
