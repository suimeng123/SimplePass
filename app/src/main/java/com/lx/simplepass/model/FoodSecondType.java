package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 菜谱二级分类
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class FoodSecondType implements Parcelable {
    private CategoryInfo categoryInfo;
    private List<FoodThreeType> childs;

    public CategoryInfo getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public List<FoodThreeType> getChilds() {
        return childs;
    }

    public void setChilds(List<FoodThreeType> childs) {
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

    public FoodSecondType() {
    }

    protected FoodSecondType(Parcel in) {
        this.categoryInfo = in.readParcelable(CategoryInfo.class.getClassLoader());
        this.childs = in.createTypedArrayList(FoodThreeType.CREATOR);
    }

    public static final Parcelable.Creator<FoodSecondType> CREATOR = new Parcelable.Creator<FoodSecondType>() {
        @Override
        public FoodSecondType createFromParcel(Parcel source) {
            return new FoodSecondType(source);
        }

        @Override
        public FoodSecondType[] newArray(int size) {
            return new FoodSecondType[size];
        }
    };
}
