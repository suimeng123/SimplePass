package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 菜谱第三级类别
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class FoodThreeType implements Parcelable {
    private CategoryInfo categoryInfo;

    public CategoryInfo getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.categoryInfo, flags);
    }

    public FoodThreeType() {
    }

    protected FoodThreeType(Parcel in) {
        this.categoryInfo = in.readParcelable(CategoryInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<FoodThreeType> CREATOR = new Parcelable.Creator<FoodThreeType>() {
        @Override
        public FoodThreeType createFromParcel(Parcel source) {
            return new FoodThreeType(source);
        }

        @Override
        public FoodThreeType[] newArray(int size) {
            return new FoodThreeType[size];
        }
    };
}
