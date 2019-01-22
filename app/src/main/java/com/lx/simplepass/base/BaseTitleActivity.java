package com.lx.simplepass.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.simplepass.R;
import com.lx.simplepass.utils.ScreenAdapterUtil;

import org.w3c.dom.Text;

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
        setLeftBtnOnclick(null);
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

    public View getTitleLayout() {
        return header;
    }

    public void setTextTitleColor(int color) {
        if (header != null) {
            ((TextView)header.findViewById(R.id.header_txt_title)).setTextColor(color);
        }
    }

    public void setTitleText(CharSequence title) {
        if (header != null) {
            TextView tv = (TextView)header.findViewById(R.id.header_txt_title);
            tv.setText(title);
        }
    }

    public void setLeftBtnOnclick(View.OnClickListener l) {
        if (header != null) {
            if (l == null) {
                l = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                };
            }
            View view = header.findViewById(R.id.header_iv_left);
            view.setOnClickListener(l);
            view.setVisibility(View.VISIBLE);
        }
    }

    public void setRightBtnClick(View.OnClickListener l) {
        if (header != null) {
            View view = header.findViewById(R.id.header_iv_right);
            view.setOnClickListener(l);
            view.setVisibility(View.VISIBLE);
        }
    }

    public void setRightTextClick(View.OnClickListener l) {
        if (header != null) {
            View view = header.findViewById(R.id.header_tv_right);
            view.setOnClickListener(l);
            view.setVisibility(View.VISIBLE);
        }
    }

    public void setLeftImgResource(int id) {
        if (header != null) {
            ImageView leftImg = (ImageView)header.findViewById(R.id.header_iv_left);
            leftImg.setImageResource(id);
            leftImg.setVisibility(View.VISIBLE);
        }
    }


    public void setRightImgResource(int id) {
        if (header != null) {
            ImageView rightImg = (ImageView)header.findViewById(R.id.header_iv_right);
            rightImg.setImageResource(id);
            rightImg.setVisibility(View.VISIBLE);
        }
    }

    public void setRightText(CharSequence text) {
        if (header != null) {
            TextView tv = header.findViewById(R.id.header_tv_right);
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        }
    }
}
