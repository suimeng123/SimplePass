package com.lx.simplepass.app;

import com.lx.simplepass.R;

/**
 * com.lx.simplepass.app
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class Constants {

    // 主页底部tab名称
    public final static int[] names = new int[]{R.string.first_tab, R.string.second_tab, R.string.three_tab, R.string.four_tab};
    //    private int[] drawableIds = new int[]{R.drawable.tab1_selector, R.drawable.tab2_selector, R.drawable.tab3_selector, R.drawable.tab4_selector};
    // 主页底部tab默认图片id
    public final static int[] imgs = new int[] {R.mipmap.tab1, R.mipmap.tab2, R.mipmap.tab3, R.mipmap.tab4};
    // 主页底部tab选中图片id
    public final static int[] sImgs = new int[] {R.mipmap.tab1_selected, R.mipmap.tab2_selected, R.mipmap.tab3_selected, R.mipmap.tab4_selected};


    public final static String SHAREDSDK_KEY = "14b21ffe43fce";

    public final static int PAGE_SIZE = 10;

    /** 存储的city类别key值 **/
    public final static String KEY_CITYS = "citys";
    /** 存储的菜谱类别key值 **/
    public final static String KEY_COOK_CATEGORY = "CookCategory";
    /** 存储的微信段子类别key值 **/
    public final static String KEY_WEIXIN_PIECE_CATEGORY = "weixinpiece";
}
