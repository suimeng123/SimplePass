package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class WeixinPieceType implements Parcelable {
    private String cid;
    private String name;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cid);
        dest.writeString(this.name);
    }

    public WeixinPieceType() {
    }

    protected WeixinPieceType(Parcel in) {
        this.cid = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<WeixinPieceType> CREATOR = new Parcelable.Creator<WeixinPieceType>() {
        @Override
        public WeixinPieceType createFromParcel(Parcel source) {
            return new WeixinPieceType(source);
        }

        @Override
        public WeixinPieceType[] newArray(int size) {
            return new WeixinPieceType[size];
        }
    };
}
