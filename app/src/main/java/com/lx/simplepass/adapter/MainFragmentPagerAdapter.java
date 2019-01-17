package com.lx.simplepass.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 主页的ViewPager适配器 有缓存的
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> fs) {
        super(fm);
        this.fragments = fs;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
