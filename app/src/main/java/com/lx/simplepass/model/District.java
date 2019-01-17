package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 县级
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/16.
 */

public class District implements Parcelable {
    private String district;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.district);
    }

    public District() {
    }

    protected District(Parcel in) {
        this.district = in.readString();
    }

    public static final Parcelable.Creator<District> CREATOR = new Parcelable.Creator<District>() {
        @Override
        public District createFromParcel(Parcel source) {
            return new District(source);
        }

        @Override
        public District[] newArray(int size) {
            return new District[size];
        }
    };

    @Override
    public String toString() {
        return "District{" +
                "district='" + district + '\'' +
                '}';
    }
}
