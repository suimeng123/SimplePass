package com.lx.simplepass.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lx.simplepass.R;
import com.lx.simplepass.adapter.BigImgPagerAdapter;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.model.FoodDetailItem;

import java.util.ArrayList;

/**
 * 查看大图工具类
 * com.lx.simplepass.activity
 * SimplePass
 * Created by lixiao2
 * 2019/1/22.
 */

public class LookBigImgActivity extends BaseActivity {

    private ViewPager viewPager;
    private BigImgPagerAdapter adapter;
    private int mPosition = 0;
    private ArrayList<FoodDetailItem> lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getIntent().getIntExtra("position", 0);
        lists = getIntent().getParcelableArrayListExtra("data");

        setContentView(R.layout.activity_look_big_img);
        setHeaderVisiable(true);

        getTitleLayout().setBackgroundColor(getResources().getColor(android.R.color.black));
        setTextTitleColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public void initLayout() {
        viewPager = findViewById(R.id.vp_img);
    }

    @Override
    public void initData() {
        if (lists != null) {
            setTitleText(String.format("%s/%s", mPosition + 1, lists.size()));
            adapter = new BigImgPagerAdapter(mContext, lists);
            viewPager.setAdapter(adapter);

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    mPosition = position;
                    setTitleText(String.format("%s/%s", mPosition + 1, lists.size()));
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            if (mPosition > 0) {
                viewPager.setCurrentItem(mPosition);
            }
        }
    }
}
