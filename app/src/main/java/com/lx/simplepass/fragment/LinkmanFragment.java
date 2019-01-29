package com.lx.simplepass.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.base.library.lib.pullrefresh.PullToRefreshBase;
import com.base.library.lib.pullrefresh.PullToRefreshListView;
import com.base.library.utils.ToastUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.adapter.LinkmanAdapter;
import com.lx.simplepass.base.BaseFragment;
import com.lx.simplepass.base.CheckPermissFragment;
import com.lx.simplepass.contentprovider.LinkmanAsyncQueryHandler;
import com.lx.simplepass.model.Linkman;
import com.lx.simplepass.utils.LinkManUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * com.lx.simplepass.fragment
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class LinkmanFragment extends BaseFragment {

    private PullToRefreshListView mLvList;
    private LinkmanAdapter adapter;
    private List lists = new ArrayList();

    private LinkmanAsyncQueryHandler queryHandler;

    private CheckPermissFragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_linkman,null);
        return view;
    }

    @Override
    public void initLayout(View view) {
        mLvList = view.findViewById(R.id.lv_list);
    }

    @Override
    public void initData() {
        adapter = new LinkmanAdapter(mContext, lists, R.layout.listitem_linkman, new DelCallBack(){

            @Override
            public void success() {
                initDataForDatabase();
            }

            @Override
            public void fail() {
            }
        });
        mLvList.setAdapter(adapter);
        queryHandler = new LinkmanAsyncQueryHandler(mContext.getContentResolver(), new LinkmanAsyncQueryHandler.QueryCallBack() {
            @Override
            public void back(List<Linkman> list) {
                if (list != null) {
                    lists.clear();
                    lists.addAll(list);
                    adapter.notifyDataSetChanged();
                    mLvList.onRefreshComplete();
                }
            }
        });

        fragment = CheckPermissFragment.getPermissFragmentForFragment(this, new CheckPermissFragment.AuthPermissionListener() {
            @Override
            public void success() {
                ToastUtil.showToast(mContext, "授权成功");
                initDataForDatabase();
            }

            @Override
            public void fail() {
                ToastUtil.showToast(mContext, "授权失败");
            }
        });

        mLvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                initDataForDatabase();
            }
        });

        mLvList.post(new Runnable() {
            @Override
            public void run() {
                fragment.checkPermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS});
            }
        });
    }

    private void initDataForDatabase() {
        LinkManUtil.sendSearchLinkmanList(mContext, queryHandler);
    }



    public interface DelCallBack {
        void success();
        void fail();
    }
}
