package com.lx.simplepass.utils;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * 公共工具类
 * com.lx.simplepass.utils
 * SimplePass
 * Created by lixiao2
 * 2019/1/21.
 */

public class CommUtils {

    public interface IEditTextListener{
        void getResult(String result);
    }
    public static void searchInputListener(final EditText editText, final IEditTextListener listener) {
        // 键盘搜索键点击监听
        TextView.OnEditorActionListener myEditListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //搜索按键action
                    String content = textView.getText().toString().trim();
                    listener.getResult(content);
                    return true;
                }
                return false;
            }
        };
        View.OnFocusChangeListener myOnFocusLinener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    // 获取焦点
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) editText.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    editText.setLayoutParams(params);
                } else {
                    // 失去焦点
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) editText.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    editText.setLayoutParams(params);
                }
            }
        };
        editText.setOnFocusChangeListener(myOnFocusLinener);
        editText.setOnEditorActionListener(myEditListener);
    }

    /** 得到一个随机数大于等于0小于4的整数 **/
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(4);
    }

    /** 获取状态栏高度 **/
    public static int getStateBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /** 设置状态栏字体颜色 false为白色 **/
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
