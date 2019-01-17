package com.lx.simplepass.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.library.utils.ViewUtil;
import com.lx.simplepass.R;

/**
 * com.lx.simplepass.views
 * SimplePass
 * Created by lixiao2
 * 2019/1/10.
 */

public class BottomTabLayout extends LinearLayout {

    private Context mContext;

    public SparseArray<View> views = new SparseArray<>();

    // 当前选中的item下标
    private int selectedIndex = -1;

    private int[] names;
    private int[] imgs;
    private int[] sImgs;

    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * 设置名称图片等资源 必须在setItemClickListener方法前调用
     * @param names 名字集合
     * @param imgs  默认图片id集合
     * @param sImgs 选中图片id集合
     */
    public void setResources(int[] names, int[] imgs, int[] sImgs) {
        this.names = names;
        this.imgs = imgs;
        this.sImgs = sImgs;
    }

    private void init() {
        // 初始化布局
        for (int i = 0; i < names.length; i++) {
            TextView textView = new TextView(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            params.weight = 1;
            textView.setText(names[i]);
            textView.setTextSize(10f);
            textView.setTextColor(mContext.getResources().getColor(R.color.c_33));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            ViewUtil.setTextViewDrawable(mContext,textView,1,imgs[i]);
            textView.setPadding(0, 20, 0, 0);
            textView.setLayoutParams(params);
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        // 设置点击回调
                        itemClickListener.onclick(finalI);
                        clickTab(finalI);
                    }
                }
            });
            addView(textView);
            views.put(i, textView);
        }
        views.get(0).performClick();
    }

    /** 点击tab项 字体和图片颜色变化 **/
    private void clickTab(int index) {
        if (selectedIndex == index) {
            return;
        }
        if (views.size() >= index) {
            TextView view = (TextView) views.get(index);
            ViewUtil.setTextViewDrawable(mContext,view,1,sImgs[index]);
            view.setTextColor(mContext.getResources().getColor(R.color.tab_selected_color));

            if (selectedIndex >= 0) {
                TextView view2 = (TextView) views.get(selectedIndex);
                ViewUtil.setTextViewDrawable(mContext, view2, 1, imgs[selectedIndex]);
                view2.setTextColor(mContext.getResources().getColor(R.color.c_33));
            }
            selectedIndex = index;
        }
    }

    public interface ItemClickListener {
        void onclick(int index);
    }
    ItemClickListener itemClickListener;
    // 设置点击事件回调
    public void setItemClickListener(ItemClickListener l) {
        this.itemClickListener = l;
        init();
    }
}
