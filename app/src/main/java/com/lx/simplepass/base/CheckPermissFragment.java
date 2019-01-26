package com.lx.simplepass.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.library.utils.ToastUtil;

/**
 * 检查权限工具fragment
 * com.lx.simplepass.fragment
 * SimplePass
 * Created by lixiao2
 * 2019/1/23.
 */

public class CheckPermissFragment extends Fragment {

    private static final String TAG = "CheckPermissFragment";

    private final static int PERMISSION_OK = 1000;

    private AuthPermissionListener listener;
    public interface AuthPermissionListener {
        void success();
        void fail();
    }

    public void addAuthPermissionListener (AuthPermissionListener l) {
        this.listener = l;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 防止重复创建fragment
        setRetainInstance(true);
    }

    public static CheckPermissFragment getPermissFragment(AppCompatActivity context, CheckPermissFragment.AuthPermissionListener listener) {
        FragmentManager manager = context.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(context.getClass().getSimpleName());
        if (fragment == null) {
            // 如果不存在 就创建一个
            FragmentTransaction transaction = manager.beginTransaction();
            CheckPermissFragment permissFragment = new CheckPermissFragment();
            transaction.add(permissFragment, context.getClass().getSimpleName());
            transaction.commit();
            manager.executePendingTransactions();
            permissFragment.addAuthPermissionListener(listener);
            return permissFragment;
        } else {
            return (CheckPermissFragment) fragment;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void checkPermission(String[] permissions) {
        String[] havePermission = isALLPermissionHave(permissions);
        if (havePermission != null) {
            if (havePermission.length == 1 && "notAsk".equals(havePermission[0])) {
                // 调用应用系统权限设置界面
            } else {
                // 没有权限，去请求系统获取权限
                requestPermissions(havePermission, PERMISSION_OK);
            }
        } else {
            // 已经有权限
            listener.success();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_OK:
                if (grantResults.length > 0 && !isAllPermissionPass(grantResults)) {
                    // 没有权限
                    ToastUtil.showToast(getActivity(), "相关权限授权失败");
                    listener.fail();
                } else {
                    // 已经有权限
                    listener.success();
                }
                break;
            default:
                break;
        }
    }

    /** 是否申请的所有权限都已经有了 **/
    private String[] isALLPermissionHave(String[] permissions) {
        String[] pers = new String[permissions.length];
        int i = 0;
        boolean isNotAsk = false;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                pers[i] = permission;
                i++;

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                    isNotAsk = true;
                }
            }
        }
        if (isNotAsk) {
            ToastUtil.showToast(getActivity(), "请设置相关权限");
            startSettingActivity(getActivity());
            return new String[]{"notAsk"};
        }
        if (i == 0) {
            return null;
        } else {
            String[] per = new String[i];
            // 将pers数据中的下标0-i之间的数据赋值到per数组中
            System.arraycopy(pers, 0, per, 0, i);
            return per;
        }
    }

    /** 是否所有权限都已授权 **/
    private boolean isAllPermissionPass(int[] grantResults) {
        boolean isAllPass = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isAllPass = false;
                break;
            }
        }
        return isAllPass;
    }

    /** 如果拒绝权限弹窗，下次进入就直接进入设置权限界面 **/
    public static void startSettingActivity(@NonNull Activity context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + context.getPackageName()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }
}
