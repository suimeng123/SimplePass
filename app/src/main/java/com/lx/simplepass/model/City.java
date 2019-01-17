package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 市级
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/16.
 */

public class City implements Parcelable {
    private String city;
    private List<District> district;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<District> getDistrict() {
        return district;
    }

    public void setDistrict(List<District> district) {
        this.district = district;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeTypedList(this.district);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.city = in.readString();
        this.district = in.createTypedArrayList(District.CREATOR);
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", district=" + district +
                '}';
    }
}
