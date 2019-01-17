package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 省级
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/16.
 */

public class Provinces implements Parcelable {
    private String province;
    private List<City> city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.province);
        dest.writeTypedList(this.city);
    }

    public Provinces() {
    }

    protected Provinces(Parcel in) {
        this.province = in.readString();
        this.city = in.createTypedArrayList(City.CREATOR);
    }

    public static final Parcelable.Creator<Provinces> CREATOR = new Parcelable.Creator<Provinces>() {
        @Override
        public Provinces createFromParcel(Parcel source) {
            return new Provinces(source);
        }

        @Override
        public Provinces[] newArray(int size) {
            return new Provinces[size];
        }
    };

    @Override
    public String toString() {
        return "Provinces{" +
                "province='" + province + '\'' +
                ", city=" + city +
                '}';
    }
}
