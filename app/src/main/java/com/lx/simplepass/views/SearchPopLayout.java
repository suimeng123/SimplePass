package com.lx.simplepass.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * 多级搜索popwindow布局
 * com.lx.simplepass.views
 * SimplePass
 * Created by lixiao2
 * 2019/1/21.
 */

public class SearchPopLayout {

    private PopupWindow popupWindow;
    private SparseArray<View> views;
    private View parentView;
    private Context mContext;

    public SearchPopLayout(Context context) {
        mContext = context;
        views = new SparseArray<>();
    }

    private PopupWindow getPopWindow(View parentView, int width, int height) {
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(parentView, width, height, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(true);
        window.update();
        return window;
    }

    public PopupWindow getInstance(int layoutId, int width, int height) {
        if (popupWindow == null) {
            parentView = LayoutInflater.from(mContext).inflate(layoutId,null);
            popupWindow = getPopWindow(parentView,width,height);
        }
        return popupWindow;
    }

    public View getView(int resId) {
        View view = null;
        if (views == null) {
            views = new SparseArray<>();
        }
        if (parentView != null) {
            if (views.get(resId) == null) {
                view = parentView.findViewById(resId);
                views.put(resId, view);
            } else {
                view = views.get(resId);
            }
        }
        return view;
    }

    // 隐藏或显示Popwindow
    public void showOrHidePopWindow(View view) {
        if (popupWindow != null && view != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
//                popupWindow.showAsDropDown(view);
                showPopLocation(popupWindow, view);
            }
        }
    }

    // 隐藏或显示Popwindow
    public void showOrHidePopWindowLocation(View view, int gravity, int x, int y) {
        if (popupWindow != null && view != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
                popupWindow.showAtLocation(view,gravity,x,y);
            }
        }
    }



    // 解决兼容问题 anroid7.0之后使用showAsDropDown有问题 7.0之前使用showAtLocation也有问题
    private void showPopLocation(PopupWindow pop, View v) {
        if (Build.VERSION.SDK_INT >= 24) {
            int[] point = new int[2];
            v.getLocationInWindow(point);
            showAsDropDown(pop, v, 0, point[1] + v.getHeight());
        } else {
            pop.showAsDropDown(v);
        }
    }

    /**
     *
     * @param pw     popupWindow
     * @param anchor v
     * @param xoff   x轴偏移
     * @param yoff   y轴偏移
     */
    public void showAsDropDown(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }


    public void destory() {
        if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
            popupWindow = null;
        }
        if (views != null) {
            views.clear();
            views = null;
        }
        if (parentView != null) {
            parentView = null;
        }
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }

    public SparseArray<View> getViews() {
        return views;
    }

    public void setViews(SparseArray<View> views) {
        this.views = views;
    }

    public View getParentView() {
        return parentView;
    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }
}
