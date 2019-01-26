package com.lx.simplepass.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lx.simplepass.R;
import com.lx.simplepass.activity.CookActivity;
import com.lx.simplepass.activity.WeixinPieceActivity;
import com.lx.simplepass.activity.WelcomeActivity;
import com.lx.simplepass.base.BaseFragment;

/**
 * 首页
 * com.lx.simplepass.fragment
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class FirstPageFragment extends BaseFragment {
    private Bundle bundle;
    private TextView text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(mContext).inflate(R.layout.fragment_first_page, container, false);
    }

    @Override
    public void initLayout(View view) {
        text = view.findViewById(R.id.text);
        text.setText(bundle.getString("title"));
        view.findViewById(R.id.txt_weather).setOnClickListener(this);
        view.findViewById(R.id.txt_food).setOnClickListener(this);
        view.findViewById(R.id.txt_weixin).setOnClickListener(this);
        view.findViewById(R.id.txt_date).setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_weather:
                break;
            case R.id.txt_food:
                // 菜谱
                startActivity(new Intent(mContext, CookActivity.class));
                break;
            case R.id.txt_weixin:
                startActivity(new Intent(mContext, WeixinPieceActivity.class));
                break;
            case R.id.txt_date:
                break;
            default:
                break;
        }
    }
}
