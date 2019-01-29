package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class Linkman implements Parcelable {

    private String name;
    private String number;
    private String sortKey;
    private int contactId;
    private long photoId;
    private String lookUpKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public String getLookUpKey() {
        return lookUpKey;
    }

    public void setLookUpKey(String lookUpKey) {
        this.lookUpKey = lookUpKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.sortKey);
        dest.writeInt(this.contactId);
        dest.writeLong(this.photoId);
        dest.writeString(this.lookUpKey);
    }

    public Linkman() {
    }

    protected Linkman(Parcel in) {
        this.name = in.readString();
        this.number = in.readString();
        this.sortKey = in.readString();
        this.contactId = in.readInt();
        this.photoId = in.readLong();
        this.lookUpKey = in.readString();
    }

    public static final Parcelable.Creator<Linkman> CREATOR = new Parcelable.Creator<Linkman>() {
        @Override
        public Linkman createFromParcel(Parcel source) {
            return new Linkman(source);
        }

        @Override
        public Linkman[] newArray(int size) {
            return new Linkman[size];
        }
    };


    @Override
    public String toString() {
        return "Linkman{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sortKey='" + sortKey + '\'' +
                ", contactId=" + contactId +
                ", photoId=" + photoId +
                ", lookUpKey='" + lookUpKey + '\'' +
                '}';
    }
}
