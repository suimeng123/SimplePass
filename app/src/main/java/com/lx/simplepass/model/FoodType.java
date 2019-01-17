package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 菜谱一级分类
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class FoodType implements Parcelable {
    private CategoryInfo categoryInfo;
    private List<FoodSecondType> childs;

    public CategoryInfo getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public List<FoodSecondType> getChilds() {
        return childs;
    }

    public void setChilds(List<FoodSecondType> childs) {
        this.childs = childs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.categoryInfo, flags);
        dest.writeTypedList(this.childs);
    }

    public FoodType() {
    }

    protected FoodType(Parcel in) {
        this.categoryInfo = in.readParcelable(CategoryInfo.class.getClassLoader());
        this.childs = in.createTypedArrayList(FoodSecondType.CREATOR);
    }

    public static final Parcelable.Creator<FoodType> CREATOR = new Parcelable.Creator<FoodType>() {
        @Override
        public FoodType createFromParcel(Parcel source) {
            return new FoodType(source);
        }

        @Override
        public FoodType[] newArray(int size) {
            return new FoodType[size];
        }
    };

    @Override
    public String toString() {
        return "FoodType{" +
                "categoryInfo=" + categoryInfo +
                ", childs=" + childs +
                '}';
    }
}
