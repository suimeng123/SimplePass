package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/25.
 */

public class WeiXinPieceList implements Parcelable {
    private int curPage;
    private int Total;
    private ArrayList<WeixinPieceItem> list;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public ArrayList<WeixinPieceItem> getList() {
        return list;
    }

    public void setList(ArrayList<WeixinPieceItem> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.curPage);
        dest.writeInt(this.Total);
        dest.writeTypedList(this.list);
    }

    public WeiXinPieceList() {
    }

    protected WeiXinPieceList(Parcel in) {
        this.curPage = in.readInt();
        this.Total = in.readInt();
        this.list = in.createTypedArrayList(WeixinPieceItem.CREATOR);
    }

    public static final Parcelable.Creator<WeiXinPieceList> CREATOR = new Parcelable.Creator<WeiXinPieceList>() {
        @Override
        public WeiXinPieceList createFromParcel(Parcel source) {
            return new WeiXinPieceList(source);
        }

        @Override
        public WeiXinPieceList[] newArray(int size) {
            return new WeiXinPieceList[size];
        }
    };
}
