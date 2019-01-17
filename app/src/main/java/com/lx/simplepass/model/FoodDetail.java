package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 菜谱详情
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class FoodDetail implements Parcelable {
    private String[] ctgIds;
    private String ctgTitles;
    private String menuId;
    private String name;
    private FoodRecipe recipe;
    private String thumbnail;

    public String[] getCtgIds() {
        return ctgIds;
    }

    public void setCtgIds(String[] ctgIds) {
        this.ctgIds = ctgIds;
    }

    public String getCtgTitles() {
        return ctgTitles;
    }

    public void setCtgTitles(String ctgTitles) {
        this.ctgTitles = ctgTitles;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(FoodRecipe recipe) {
        this.recipe = recipe;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(this.ctgIds);
        dest.writeString(this.ctgTitles);
        dest.writeString(this.menuId);
        dest.writeString(this.name);
        dest.writeParcelable(this.recipe, flags);
        dest.writeString(this.thumbnail);
    }

    public FoodDetail() {
    }

    protected FoodDetail(Parcel in) {
        this.ctgIds = in.createStringArray();
        this.ctgTitles = in.readString();
        this.menuId = in.readString();
        this.name = in.readString();
        this.recipe = in.readParcelable(FoodRecipe.class.getClassLoader());
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<FoodDetail> CREATOR = new Parcelable.Creator<FoodDetail>() {
        @Override
        public FoodDetail createFromParcel(Parcel source) {
            return new FoodDetail(source);
        }

        @Override
        public FoodDetail[] newArray(int size) {
            return new FoodDetail[size];
        }
    };
}
