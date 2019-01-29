package com.lx.simplepass.views;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lx.simplepass.R;

/**
 * 公共弹出窗口
 * com.lx.simplepass.views
 * SimplePass
 * Created by lixiao2
 * 2019/1/28.
 */

public class CommDialog extends Dialog {

    public CommDialog(@NonNull Context context) {
        super(context);
    }

    public CommDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init(Context context) {

    }

    public static class Builder {
        private CommDialog dialog;
        private Button mBtnSure,mBtnClose;
        private TextView mTip;
        public Builder(Context context) {
            dialog = new CommDialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_tip, null);
            dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mBtnSure = view.findViewById(R.id.btn_sure);
            mBtnClose = view.findViewById(R.id.btn_cancle);
            mBtnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            mTip = view.findViewById(R.id.tv_tip);
        }

        public CommDialog create() {
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }

        public Builder setTip(CharSequence text) {
            mTip.setText(text);
            return this;
        }

        public Builder setSureClick(View.OnClickListener listener) {
            mBtnSure.setOnClickListener(listener);
            return this;
        }

        public Builder setCancleClick(View.OnClickListener listener) {
            mBtnClose.setOnClickListener(listener);
            return this;
        }
    }
}
