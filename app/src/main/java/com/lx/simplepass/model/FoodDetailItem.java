package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 步骤item
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/18.
 */

public class FoodDetailItem implements Parcelable {

    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.step);
    }

    public FoodDetailItem() {
    }

    protected FoodDetailItem(Parcel in) {
        this.img = in.readString();
        this.step = in.readString();
    }

    public static final Parcelable.Creator<FoodDetailItem> CREATOR = new Parcelable.Creator<FoodDetailItem>() {
        @Override
        public FoodDetailItem createFromParcel(Parcel source) {
            return new FoodDetailItem(source);
        }

        @Override
        public FoodDetailItem[] newArray(int size) {
            return new FoodDetailItem[size];
        }
    };
}
