package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 菜品分类详情
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class CategoryInfo implements Parcelable {
    private String ctgId;
    private String name;
    private String parentId;

    public String getCtgId() {
        return ctgId;
    }

    public void setCtgId(String ctgId) {
        this.ctgId = ctgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ctgId);
        dest.writeString(this.name);
        dest.writeString(this.parentId);
    }

    public CategoryInfo() {
    }

    protected CategoryInfo(Parcel in) {
        this.ctgId = in.readString();
        this.name = in.readString();
        this.parentId = in.readString();
    }

    public static final Parcelable.Creator<CategoryInfo> CREATOR = new Parcelable.Creator<CategoryInfo>() {
        @Override
        public CategoryInfo createFromParcel(Parcel source) {
            return new CategoryInfo(source);
        }

        @Override
        public CategoryInfo[] newArray(int size) {
            return new CategoryInfo[size];
        }
    };

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "ctgId='" + ctgId + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
