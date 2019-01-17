package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 菜的做法类
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class FoodRecipe implements Parcelable {
    private String img;
    private String ingredients;
    private String method;
    private String sumary;
    private String title;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.ingredients);
        dest.writeString(this.method);
        dest.writeString(this.sumary);
        dest.writeString(this.title);
    }

    public FoodRecipe() {
    }

    protected FoodRecipe(Parcel in) {
        this.img = in.readString();
        this.ingredients = in.readString();
        this.method = in.readString();
        this.sumary = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<FoodRecipe> CREATOR = new Parcelable.Creator<FoodRecipe>() {
        @Override
        public FoodRecipe createFromParcel(Parcel source) {
            return new FoodRecipe(source);
        }

        @Override
        public FoodRecipe[] newArray(int size) {
            return new FoodRecipe[size];
        }
    };
}
