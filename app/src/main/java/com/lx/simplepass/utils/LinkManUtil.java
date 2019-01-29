package com.lx.simplepass.utils;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.provider.ContactsContract;

import com.lx.simplepass.contentprovider.LinkmanAsyncQueryHandler;

import java.util.ArrayList;

/**
 * com.lx.simplepass.utils
 * SimplePass
 * Created by lixiao2
 * 2019/1/29.
 */

public class LinkManUtil {

    /** 删除联系人 **/
    public static void delLinkman(Context context, int linkmanId) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI)
                .withSelection(ContactsContract.Data._ID + "=?", new String[]{String.valueOf(linkmanId)})
                .build());
        context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
    }

    public static void sendSearchLinkmanList(Context context, LinkmanAsyncQueryHandler queryHandler) {

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projections = {
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DATA1,
                ContactsContract.Contacts.SORT_KEY_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY
        };
        ContentResolver reslover = context.getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 5.0 以上 ，sort_key_primary 是原汉字，排序字段变成了 phonebook_label
            projections[3] = "phonebook_label";
            Cursor testCursor = null;
            try {
                testCursor = reslover.query(uri, new String[]{"phonebook_label"}, null, null, null);
            }catch (Exception e){
                //有些手机厂商 没有 phonebook_label 这个字段
                projections[3] = ContactsContract.Contacts.SORT_KEY_PRIMARY;
            }finally {
                if (testCursor!=null){
                    testCursor.close();
                }
            }
        }
        // 按照sort_key升序查询
        queryHandler.startQuery(0, null, uri, projections,null, null, "sort_key COLLATE LOCALIZED asc");
    }
}
