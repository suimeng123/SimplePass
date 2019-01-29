package com.lx.simplepass.contentprovider;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

import com.lx.simplepass.model.Linkman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.lx.simplepass.contentprovider
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class LinkmanAsyncQueryHandler extends AsyncQueryHandler {

    public interface QueryCallBack{
        void back(List<Linkman> list);
    }

    private QueryCallBack mCallBack;

    public LinkmanAsyncQueryHandler(ContentResolver cr, QueryCallBack callBack) {
        super(cr);
        mCallBack = callBack;
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            // 有数据
            List<Linkman> lists = new ArrayList<>();
            // 游标移到第一项
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                Linkman linkman = new Linkman();
                linkman.setName(cursor.getString(1));
                linkman.setNumber(cursor.getString(2));
                linkman.setSortKey(cursor.getString(3));
                linkman.setContactId(cursor.getInt(4));
                linkman.setPhotoId(cursor.getLong(5));
                linkman.setLookUpKey(cursor.getString(6));
                lists.add(linkman);
            }
            mCallBack.back(lists);
        }
        mCallBack.back(null);
        super.onQueryComplete(token, cookie, cursor);
    }
}
