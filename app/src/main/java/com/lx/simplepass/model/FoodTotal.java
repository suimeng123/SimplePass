package com.lx.simplepass.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 菜谱列表结果集
 * com.lx.simplepass.model
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class FoodTotal implements Parcelable {
    private String curPage;
    private List<FoodDetail> list;
    private String total;

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public List<FoodDetail> getList() {
        return list;
    }

    public void setList(List<FoodDetail> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.curPage);
        dest.writeTypedList(this.list);
        dest.writeString(this.total);
    }

    public FoodTotal() {
    }

    protected FoodTotal(Parcel in) {
        this.curPage = in.readString();
        this.list = in.createTypedArrayList(FoodDetail.CREATOR);
        this.total = in.readString();
    }

    public static final Parcelable.Creator<FoodTotal> CREATOR = new Parcelable.Creator<FoodTotal>() {
        @Override
        public FoodTotal createFromParcel(Parcel source) {
            return new FoodTotal(source);
        }

        @Override
        public FoodTotal[] newArray(int size) {
            return new FoodTotal[size];
        }
    };
}
