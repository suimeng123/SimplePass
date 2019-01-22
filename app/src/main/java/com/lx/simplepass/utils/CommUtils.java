package com.lx.simplepass.utils;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lx.simplepass.views.SearchInputLayout;

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
}
