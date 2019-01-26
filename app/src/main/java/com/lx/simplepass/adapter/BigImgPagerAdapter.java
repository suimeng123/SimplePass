package com.lx.simplepass.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.simplepass.R;
import com.lx.simplepass.model.FoodDetailItem;
import com.lx.simplepass.utils.ImageLoaderUtil;

import java.util.ArrayList;

/**
 * 大图适配器 不带缓存
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/22.
 */

//public class BigImgPagerAdapter extends FragmentStatePagerAdapter {
//
//    private List<Fragment> fragments;
//
//    public BigImgPagerAdapter(FragmentManager fm, List<Fragment> list) {
//        super(fm);
//        fragments = list;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return fragments.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return fragments.size();
//    }
//}
    //默认三页缓存
public class BigImgPagerAdapter extends PagerAdapter {

    private ArrayList<FoodDetailItem> mDatas;
    private Context mContext;

    public  BigImgPagerAdapter (Context context, ArrayList<FoodDetailItem> list) {
        mDatas = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_big_img, container, false);
        container.addView(view);
        return view;
    }

    // 来判断是否显示的同一页
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        FoodDetailItem item = mDatas.get(position);
        ImageView icon = ((View)object).findViewById(R.id.iv_img);
        final TextView content = ((View) object).findViewById(R.id.tv_content);
        content.setText(item.getStep());
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content.getVisibility() == View.VISIBLE) {
                    content.setVisibility(View.GONE);
                } else {
                    content.setVisibility(View.VISIBLE);
                }
            }
        });
        ImageLoaderUtil.loadImgForUrl(mContext, item.getImg(), icon);
    }
}