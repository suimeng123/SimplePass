package com.lx.simplepass.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.telephony.PhoneNumberUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.base.library.utils.ToastUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.model.Linkman;

import java.util.List;

/**
 * 拨打电话或者发短信弹出窗
 * com.lx.simplepass.fragment
 * SimplePass
 * Created by lixiao2
 * 2019/1/28.
 */

public class MessageDialogFragment extends DialogFragment {

    private Linkman linkman;
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = this.getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            // 这里设置宽度为全屏
            dialog.getWindow().setLayout(dm.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        // 设置底部弹出显示的DialogFragment窗口属性。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT; // 底部弹出的DialogFragment的高度，如果是MATCH_PARENT则铺满整个窗口
        window.setAttributes(params);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linkman = getArguments().getParcelable("item");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 去掉标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.dialog_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.tv_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送短信
                if (linkman != null) {
                    String number = trim(linkman.getNumber());
                    if(PhoneNumberUtils.isGlobalPhoneNumber(number)){
                        // 调用系统短信界面发送短信
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
                        intent.putExtra("sms_body", "");
                        startActivity(intent);


                        // 直接调用短信接口发送短信
                        // 获取短信管理器
//                        android.telephony.SmsManager smsManager = android.telephony.SmsManager
//                                .getDefault();
//                        // 拆分短信内容（手机短信长度限制）
//                        List<String> divideContents = smsManager.divideMessage(message);
//                        for (String text : divideContents) {
//                            smsManager.sendTextMessage(phoneNumber, null, text, sentPI,
//                                    deliverPI);
//                        }
                    } else {
                        ToastUtil.showToast(getActivity(), "手机号码格式错误");
                    }
                }
            }
        });

        view.findViewById(R.id.tv_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 拨打电话
                if (linkman != null) {
                    // 直接拨打电话
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    // 跳到电话界面 手动输入
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + trim(linkman.getNumber()));
                    intent.setData(data);
                    startActivity(intent);
                } else {
                    ToastUtil.showToast(getActivity(), "电话为空");
                }
            }
        });
    }


    /** 除去空格 **/
    private String trim(String number) {
        return number.replace(" ", "");
    }
}
